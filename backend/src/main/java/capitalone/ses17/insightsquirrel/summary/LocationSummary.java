package capitalone.ses17.insightsquirrel.summary;

/**
 * Summaries of transactions based on location
 *
 * @author Alex Johnson
 */
public interface LocationSummary extends DataSummary {

    /** Radius of query in miles */
    public double searchRadius();

    public double getLogitude();

    public double getLatitude();

}
