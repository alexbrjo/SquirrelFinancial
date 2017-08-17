package capitalone.ses17.insightsquirrel.summary;

import capitalone.ses17.insightsquirrel.summary.model.Transaction;
import sun.util.calendar.CalendarDate;

import java.util.*;

public class FixedTransactionData implements ScheduleSummary {

    List<Transaction> transactions;

    public FixedTransactionData (List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public TimePeriod getTimePeriod() {
        return null;
    }

    @Override
    public String getPattern() {
        return null;
    }

    @Override
    public double getCertainty() {
        return 0;
    }

    @Override
    public String getSummaryName() {
        return null;
    }

    /**
     * Heart of the beast. Can find patterns for transactions that occur semi-regularly on:
     *      an hour of day  / daily
     *      a day of week   / weekly
     *      a day of month  / monthly
     *
     * The implementation considers large data sets and tries to minimize the number of unused
     * collections.
     *
     * @param timePeriod
     */
    private void findPatternForTransactions(TimePeriod timePeriod) {

        if (transactions.size() <= 1) {
            // not enough transactions to make judgement
            return;
        }

        /* creates grouped transactions
         *
         *       S x
         *  day  M x x
         *  of   T x x x x x
         * week  W x
         *       H
         *       R x
         *       S x x
         */
        Map<String, List<Transaction>> groups = new HashMap<String, List<Transaction>>();
        for (Transaction t : transactions) {
            List<Transaction> group = groups.get(getGroupIdByTimePeriod(timePeriod, t.getDate()));
            if (group == null) {
                group = new ArrayList<Transaction>();
            }
            group.add(t);
        }

        /* Sorts groups by number of transactions
         *
         *       T x x x x x
         *       S x x
         *  day  M x x
         * of    W x
         * week  S x
         *       R x
         *       H
         */
        List<Map.Entry<String, List<Transaction>>> group = new ArrayList<>();
        for (Map.Entry<String, List<Transaction>> a : groups.entrySet()) {
            group.add(a);
        }
        if (group.size() > 1) { // if sorting does anything
            Collections.sort(group, new Comparator<Map.Entry<String, List<Transaction>>>() {
                @Override
                public int compare(Map.Entry<String, List<Transaction>> o1, Map.Entry<String, List<Transaction>> o2) {
                    if (o1.getValue().size() < o2.getValue().size()) {
                        return -1;
                    } else if (o1.getValue().size() > o2.getValue().size()) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            });
        }

        /*
         * Finds the certainty of a transaction pattern
         */
        if (group.size() > 1) {
            int canidateSize = group.get(0).getValue().size();
            int rivalSize = group.get(1).getValue().size();

            double certainty = (canidateSize - rivalSize) / canidateSize;
        } else {
            /* certainy 100% if group size one and transactions > 1 */
            return;
        }
    }

    private String getGroupIdByTimePeriod(TimePeriod period, CalendarDate date) {
        if (TimePeriod.WEEK.equals(period)) {
            return date.getDayOfWeek() + "";
        } else {
            // no op alg
            return "0";
        }
    }

}
