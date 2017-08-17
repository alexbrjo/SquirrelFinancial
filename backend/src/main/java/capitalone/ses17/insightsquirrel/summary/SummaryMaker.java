package capitalone.ses17.insightsquirrel.summary;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Creates summaries of transactions to be consumed by the core API
 *
 * @author Alex Johnson
 */

@Controller
public class SummaryMaker {

    public DataSummary getAllSummary() {
        return null;
    }

    public LocationSummary getLocationSummary(double radius, double latitude, double longitude){
        return null;
    }

    public List<ScheduleSummary> getScheduleSummaries () {
        return null;
    }

    public void processTransactions() {}

}
