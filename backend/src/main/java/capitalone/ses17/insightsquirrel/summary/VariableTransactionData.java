package capitalone.ses17.insightsquirrel.summary;

import capitalone.ses17.insightsquirrel.summary.model.Transaction;
import sun.util.calendar.CalendarDate;

import java.util.*;

/**
 * TransactionData that typically needs some sort of parameters
 *
 * @author Alex Johnson
 */
public class VariableTransactionData implements LocationSummary, BudgetSummary {

    private List<Transaction> transactions;
    private List<CalculatedCategory> categories;
    private DateRange range;

    public VariableTransactionData (List<Transaction> transactions, DateRange range) {
        this.transactions = transactions;
    }

    @Override
    public double searchRadius() {
        return 0;
    }

    @Override
    public double getLogitude() {
        return 0;
    }

    @Override
    public double getLatitude() {
        return 0;
    }

    @Override
    public String getSummaryName() {
        return null;
    }

    @Override
    public String getCategory() {
        return null;
    }

    public DateRange getRangeStart() {
        return null;
    }

    public DateRange getRangeEnd() {
        return null;
    }

    private void generateLocationData (double latitude, double logitude, double radius) {
        /* FIXME Query elasticsearch for location */
    }

    /**
     * Main alg, creates categorized budget
     */
    private void generateBudgetData (DateRange range) {

        /* FIXME Query elasticsearch for range */

        /* Make categories
         *
         * Transactions
         *   - McDonald's  $5
         *   - ABC Store   $15
         *   - Cook out    $5
         *   - Groceries   $20
         *   - Bar         $5
         */
        Map<String, List<Transaction>> byCategory = new HashMap<String, List<Transaction>>();
        for (Transaction t : transactions) {
            List<Transaction> category = byCategory.get(squashCategories(t));
            if (category == null) {
                category = new ArrayList<Transaction>();
            }
            category.add(t);
        }

        /* Process categories
         *
         * Transactions
         *   - Fast food  $10
         *   - Alcohol    $20
         *   - Groceries  $20
         */
        double grandTotal = 0;
        List<CalculatedCategory> categories = new ArrayList<CalculatedCategory>();
        for (Map.Entry<String, List<Transaction>> c : byCategory.entrySet()) {
            double total = 0;
            for (Transaction transaction : c.getValue()) {
                total += transaction.getAmmount();
            }
            categories.add(new CalculatedCategory(c.getKey(), total));
            grandTotal += total;
        }

        /* Update percent of budget
         *
         * Transactions
         *   - Fast food  $10  20%
         *   - Alcohol    $20  40%
         *   - Groceries  $20  40%
         */
        for (CalculatedCategory cat : categories) {
            cat.percentOfBudget = cat.total / grandTotal;
        }

        this.categories = categories;
    }

    private String squashCategories (Transaction t) {
        return t.getCategories().get(0);
    }

    public static class CalculatedCategory {
        private String category;
        private double percentOfBudget;
        private double total;

        public CalculatedCategory (String category, double total) {
            this.category = category;
            this.total = total;
        }

        public String getCategory () {
            return category;
        }

        public double getPercentOfBudget () {
            return percentOfBudget;
        }

        public double getTotal () {
            return total;
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
