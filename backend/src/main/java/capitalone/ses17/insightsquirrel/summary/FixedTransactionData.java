package capitalone.ses17.insightsquirrel.summary;

import capitalone.ses17.insightsquirrel.summary.model.Transaction;

import java.util.List;

public class FixedTransactionData implements BudgetSummary, ScheduleSummary {

    public FixedTransactionData (List<Transaction> transactions) {

    }

    @Override
    public String getCategory() {
        return null;
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
    public double getCertanty() {
        return 0;
    }

    @Override
    public String getSummaryName() {
        return null;
    }

    @Override
    public DateRange getRangeStart() {
        return null;
    }

    @Override
    public DateRange getRangeEnd() {
        return null;
    }
}
