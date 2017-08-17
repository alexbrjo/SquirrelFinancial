package capitalone.ses17.insightsquirrel.speech;

import java.util.concurrent.atomic.AtomicLong;


import com.jayway.jsonpath.JsonPath;
import org.springframework.web.bind.annotation.*;

@RestController
public class ResponseController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    /*
    POST endpoint that receives JSON data and formulates a response to send to Alexa.
    Default response is null if no response can be created.
     */
    @RequestMapping(value = "/response", method = RequestMethod.POST,
            consumes = "application/json")
    public String response(@RequestBody String payload) {
        String intent = JsonPath.read(payload, "$.request.intent.name");
        String response = null;
        switch (intent) {
            case "Budget":
                response = budgetResponse(payload);
                break;
            case "Data":
                response = dataResponse(payload);
                break;
            case "Location":
                response = locationResponse(payload);
                break;
            case "Schedule":
                response = scheduleResponse(payload);
                break;
            case "Credit":
                response = creditResponse(payload);
                break;
            case "CreditCard":
                response = creditCardResponse(payload);
                break;
            default:
                break;
        }
        return response;
    }

    public String budgetResponse(String payload) {

        String response = "You have spent " + " percent of your budget on food, " + " percent on ";
        return response;
    }

    public String dataResponse(String payload) {
        String r = null;
        return r;
    }

    public String locationResponse(String payload) {
        String totalSpent = JsonPath.read(payload, "$.request.intent.slots");
        String location = JsonPath.read(payload, "$.request.intent.slots");
        String response = "You spent " + totalSpent + " dollars in ";
        return response;
    }

    public String scheduleResponse(String payload) {
        String r = null;
        return r;
    }

    public String creditResponse(String payload) {
        String response = "Credit allows you to buy something now with an agreement to pay for it later. This can be in the form of loans or credit cards";
        return response;
    }

    public String creditCardResponse(String payload){
        String response = "A credit card allows you to use credit to make purchases up to a maximum credit limit. You can keep a balance and pay the money back over time while paying interest.";
        return response;
    }
}
