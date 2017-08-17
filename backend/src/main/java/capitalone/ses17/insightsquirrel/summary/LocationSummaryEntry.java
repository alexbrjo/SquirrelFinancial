package capitalone.ses17.insightsquirrel.summary;

import capitalone.ses17.insightsquirrel.summary.model.Transaction;
import sun.util.calendar.CalendarDate;

import java.util.*;

/**
 * TransactionData that typically needs some sort of parameters
 *
 * @author Alex Johnson
 */
public class LocationSummaryEntry extends SummaryEntry {

    private List<Transaction> transactions;

    public LocationSummaryEntry(List<Transaction> transactions, double latitude, double logitude, double radius) {
        this.transactions = transactions;
        generateLocationData(latitude, logitude, radius);
    }

    private void generateLocationData (double latitude, double logitude, double radius) {

        /* FIXME Query elasticsearch for location */
        double total = 0;
        for (Transaction transaction : transactions) {
            total += transaction.getAmmount();
        }

    }
}
