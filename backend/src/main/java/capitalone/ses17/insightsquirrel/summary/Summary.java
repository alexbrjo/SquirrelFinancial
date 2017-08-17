package capitalone.ses17.insightsquirrel.summary;

import java.util.List;

/**
 * A transaction summary for a custom data set
 *
 * @author Alex Johnson
 */
public class Summary {

    private List<SummaryEntry> entries;

    public static class SummaryEntry {
        public String location;
        public String category;
        public VariableTransactionData.DateRange range;
    }
}
