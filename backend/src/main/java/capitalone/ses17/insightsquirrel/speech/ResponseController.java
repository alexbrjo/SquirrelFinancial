package capitalone.ses17.insightsquirrel.speech;

import capitalone.ses17.insightsquirrel.summary.*;
import com.jayway.jsonpath.JsonPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ResponseController {

    String DEFAULT_RESPONSE = "I'm sorry, I'm unable to find financial data on that request.";

    @Autowired
    private SummaryMaker summaryMaker = new SummaryMaker();

    /*
     * POST endpoint that receives JSON data and formulates a response to send to Alexa.
     * Default response is null if no response can be created.
     */
    @RequestMapping(value = "/response", method = RequestMethod.POST, consumes = "application/json")
    public String response(@RequestBody String payload) {
        String intent = JsonPath.read(payload, "$.request.intent.name");


        if (intent.equals("DateLocationCategory")) {
            String fromDate = JsonPath.read(payload, "$.request.intent.slots.fromdate.value");
            String toDate = JsonPath.read(payload, "$.request.intent.slots.todate.value");

            if (fromDate.contains("2018")) {
                fromDate = fromDate.replace("2018", "2017");
                toDate = toDate.replace("2018", "2017");
            }

            String city = fixNull(JsonPath.read(payload, "$.request.intent.slots.City.value"));
            String state = fixNull(JsonPath.read(payload, "$.request.intent.slots.State.value"));
            String category = JsonPath.read(payload, "$.request.intent.slots.Category.value");
            String location = fixEmpty(city + " " + state);
            String response = pastSpendingIntent(location, fromDate, toDate, category);
            return response == null ? DEFAULT_RESPONSE : response;
        } else if (intent.equals("FutureSpending")) {
            String toDate = "2017-08-18";
            String fromDate = "2017-08-04";
            String timePeriod = JsonPath.read(payload, "$.request.intent.slots.timeperiod.value");
            String name = JsonPath.read(payload, "$.request.intent.slots.futuremerchant.value");
            String response = futureSpendingIntent(fromDate, toDate, name, timePeriod);
            return response == null ? DEFAULT_RESPONSE : response;
        } else if (intent.equals("Distribution")) {
            String fromDate = JsonPath.read(payload, "$.request.intent.slots.fromdate.value");
            String toDate = JsonPath.read(payload, "$.request.intent.slots.todate.value");
            String city = fixNull(JsonPath.read(payload, "$.request.intent.slots.City.value"));
            String state = fixNull(JsonPath.read(payload, "$.request.intent.slots.State.value"));
            String response = distributionIntent(fromDate, toDate);
            return response == null ? DEFAULT_RESPONSE : response;
        } else {
            return DEFAULT_RESPONSE;
        }
    }

    private String pastSpendingIntent(String location, String fromDate, String toDate, String category) {
        String response = null;
        double r = 20;
        PastSpendingSummary summary = summaryMaker.getPastSpending(r, location, fromDate, toDate, category);

        response = String.format("From %s to %s, you spent $%.2f",
                summary.fromDate, summary.toDate, summary.total);
        if (summary.category != null) {
            response += String.format(" on %s", summary.category);
        }
        if (summary.location != null) {
            response += String.format(" in %s", summary.location);
        }
        response += String.format(" over %d purchases", summary.purchases);

        return response;
    }

    private String futureSpendingIntent(String fromDate, String toDate, String name, String timePeriod) {
        String response = null;
        FutureSpendingSummary summary = summaryMaker.getFutureSpending(name, fromDate, toDate);

        int multiplier = 7;
        String period = "week";
        if (!timePeriod.contains("W")) {
            multiplier = 30;
            period = "month";
        }
        response = String.format("I'm guessing you will spend $%.2f in the next %s", summary.total * multiplier, period);
        if (summary.name != null) {
            response += String.format(" on %s", summary.name);
        }
        response += String.format(" based on %d purchases", summary.purchases);

        return response;
    }

    private String distributionIntent (String fromDate, String toDate) {
        String response = null;
        SpendingAdviceSummary summary = summaryMaker.getSpendingAdvice(fromDate, toDate);

        response = String.format("$%.2f of your budget was spent on ", summary.category);
        if (summary.category != null) {
            response += String.format(" on %s", summary.category);
        }
        response += String.format(" based on %d purchases", summary.purchases);

        return response;
    }


    private String fixNull(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }

    private String fixEmpty(String str) {
        if ("".equals(str)) {
            return null;
        }
        return str;
    }

}
