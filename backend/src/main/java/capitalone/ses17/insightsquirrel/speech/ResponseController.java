package capitalone.ses17.insightsquirrel.speech;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import javax.xml.bind.DatatypeConverter;


import capitalone.ses17.insightsquirrel.summary.*;
import com.jayway.jsonpath.JsonPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ResponseController {
    @Autowired
    private SummaryMaker summaryMaker;
    /*
    POST endpoint that receives JSON data and formulates a response to send to Alexa.
    Default response is null if no response can be created.
     */
    @RequestMapping(value = "/response", method = RequestMethod.POST,
            consumes = "application/json")
    public String response(@RequestBody String payload) {
        String intent = JsonPath.read(payload, "$.request.intent.name");
        // default response if request fails
        String response = "I'm sorry, I'm unable to find financial data on that request.";
//        String datePeriod = JsonPath.read(payload, "$.request.intent.slots.dateperiodslot.value");
//        Calendar calendar = DatatypeConverter.parseDateTime(datePeriod);
//        Date date = calendar.getTime();
//        response = date.toString();

      //  String location = JsonPath.read(payload, "$.request.intent.slots.regionslot.value");
        //String category = JsonPath.read(payload, "$.request.intent.slots.category.value");

        double r = 3.14;
        Summary summary = summaryMaker.getSummary(r, "{}", null, null,"{}" );

        response = String.format("From %s to %s, you spent %f on %s", summary.fromDate, summary.toDate, summary.total, summary.category);
        return response;
    }

    /*
    public String budgetResponse(BudgetSummary summary) {

        String response = "You have spent " + " percent of your budget on food, " + " percent on ";
        return response;
    }

    public String dataResponse(DataSummary summary) {
        String r = null;
        return r;
    }

    public String locationResponse(LocationSummary summary) {

        String response = "You spent " +  " dollars in ";
        return response;
    }

    public String scheduleResponse(List<ScheduleSummary> summary) {

        String r = null;
        return r;
    }
    */
}
