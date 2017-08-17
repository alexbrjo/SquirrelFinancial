package capitalone.ses17.insightsquirrel.summary;

import capitalone.ses17.insightsquirrel.summary.model.Transaction;

import java.util.List;

public class FixedTransactionData implements ScheduleSummary {

    public FixedTransactionData (List<Transaction> transactions) {

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

}
