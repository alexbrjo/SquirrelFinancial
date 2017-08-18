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

        String fromDate = JsonPath.read(payload, "$.request.intent.slots.fromdate.value");
        String toDate = JsonPath.read(payload, "$.request.intent.slots.todate.value");

        String city = fixNull(JsonPath.read(payload, "$.request.intent.slots.City.value"));
        String state = fixNull(JsonPath.read(payload, "$.request.intent.slots.State.value"));
        String location = fixEmpty(city + " " + state);

        String category = JsonPath.read(payload, "$.request.intent.slots.Category.value");
        String name = JsonPath.read(payload, "$.request.intent.slots.Name.value");

        if (intent.equals("Spending")) {
            String reponse = pastSpendingIntent(location, fromDate, toDate, category);
            return reponse == null ? DEFAULT_RESPONSE : reponse;
        } else if (intent.equals("FutureSpending")) {
            String reponse = futureSpendingIntent(fromDate, toDate, name);
            return reponse == null ? DEFAULT_RESPONSE : reponse;
        } else if (intent.equals("Distribution")) {
            String reponse = distributionIntent(location, fromDate, toDate, category);
            return reponse == null ? DEFAULT_RESPONSE : reponse;
        } else {
            return DEFAULT_RESPONSE;
        }
    }

    private String pastSpendingIntent (String location, String fromDate, String toDate, String category) {
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

    private String futureSpendingIntent (String fromDate, String toDate, String name) {
        String response = null;
        FutureSpendingSummary summary = summaryMaker.getFutureSpending(fromDate, toDate, name);

        response = String.format("I'm guessing you will spend $%.2f in the next week", summary.total);
        if (summary.name != null) {
            response += String.format(" on %s", summary.name);
        }
        response += String.format(" based on %d purchases", summary.purchases);

        return response;
    }


    private String distributionIntent (String location, String fromDate, String toDate, String category) {
        String response = null;

        return response;
    }


    private String fixNull (String str) {
        if (str == null) {
            return "";
        }
        return str;
    }

    private String fixEmpty (String str) {
        if ("".equals(str)) {
            return null;
        }
        return str;
    }

}
