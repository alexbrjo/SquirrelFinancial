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
    private DateRange range;

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

    public static class DateRange {

        private CalendarDate start;

        private CalendarDate end;

        public DateRange (CalendarDate start, CalendarDate end) {
            this.start = start;
            this.end = end;
        }

        public CalendarDate getStart () {
            return start;
        }

        public CalendarDate getEnd(){
            return end;
        }
    }
}
