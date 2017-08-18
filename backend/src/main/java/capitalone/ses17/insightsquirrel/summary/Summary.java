package capitalone.ses17.insightsquirrel.summary;

import capitalone.ses17.insightsquirrel.summary.model.Transaction;

import java.util.Date;
import java.util.List;

/**
 * A transaction summary for a custom data set
 *
 * @author Alex Johnson
 */
public class Summary {

    public Summary () {}

    public Summary (List<Transaction> transactions) {}

    /* all never null */
    public String location;
    public String category;
    public Date toDate;
    public Date fromDate;
    public String humanizedDate;
    public double total;

    private List<SummaryEntry> entries;
}
