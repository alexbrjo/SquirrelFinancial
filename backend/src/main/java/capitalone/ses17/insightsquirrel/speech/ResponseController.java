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

        String reponse = pastSpendingIntent(location, fromDate, toDate, category);
        return reponse == null ? DEFAULT_RESPONSE : reponse;
    }

    private String pastSpendingIntent (String location, String fromDate, String toDate, String category) {
        String response = null;

        double r = 20;
        PastSpendingSummary summary = summaryMaker.getPastSpending(r, location, fromDate, toDate, category);

        response = String.format("From %s to %s, you spent $%.2f", summary.fromDate, summary.toDate, summary.total);
        if (summary.category != null) {
            response += String.format(" over %d purchases on %s", summary.purchases, summary.category);
        }
        if (summary.location != null) {
            response += String.format(" in %s", summary.location);
        }

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
