package capitalone.ses17.insightsquirrel.summary;

import java.util.List;

/**
 * Creates summaries of transactions to be consumed by the core API
 *
 * @author Alex Johnson
 */
public class SummaryMaker {

    private static SummaryMaker instance;

    private SummaryMaker(){}

    private static SummaryMaker getInstance () {
        if (instance == null) {
            instance = new SummaryMaker();
        }
        return instance;
    }

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
