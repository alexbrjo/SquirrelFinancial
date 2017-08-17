package capitalone.ses17.insightsquirrel.summary;

import java.util.List;

/**
 * A transaction summary for a custom data set
 *
 * @author Alex Johnson
 */
public class Summary {

    /* all never null */
    public String location;
    public String category;
    public LocationSummaryEntry.DateRange range;
    public double total;

    private List<SummaryEntry> entries;
}
