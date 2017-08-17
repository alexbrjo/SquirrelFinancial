package capitalone.ses17.insightsquirrel.summary;

import capitalone.ses17.insightsquirrel.summary.model.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryBudgetSummaryEntry extends SummaryEntry {

    public List<CalculatedCategory> categories;
    private List<Transaction> transactions;

    public CategoryBudgetSummaryEntry (List<Transaction> transactions){
        this.transactions = transactions;
    }

    /**
     * Main alg, creates categorized budget
     */
    private void generateBudgetData (LocationSummaryEntry.DateRange range) {

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
}
