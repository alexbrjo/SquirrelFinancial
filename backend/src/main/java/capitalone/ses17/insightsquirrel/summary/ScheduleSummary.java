package capitalone.ses17.insightsquirrel.summary;

/**
 * Summaries transactions based on when they occur
 *
 * @author Alex Johnson
 */
public interface ScheduleSummary extends DataSummary {

    public TimePeriod getTimePeriod();

    /** Will return something like 'every Monday' or 'towards the end of the month' */
    public String getPattern();

    /** Will be 0.00 to 1.00 */
    public double getCertainty();

    public static enum TimePeriod {
        HOUR,
        DAY,
        WEEK,
        MONTH,
        YEAR
    }

}
