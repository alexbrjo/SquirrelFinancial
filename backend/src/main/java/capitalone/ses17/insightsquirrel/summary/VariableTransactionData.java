package capitalone.ses17.insightsquirrel.summary;

import capitalone.ses17.insightsquirrel.summary.model.Transaction;

import java.util.Date;
import java.util.List;

public class VariableTransactionData implements LocationSummary, BudgetSummary {

    public VariableTransactionData (List<Transaction> transactions, DateRange range) {

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

    public static class DateRange {

        private Date start;

        private Date end;

        public DateRange (Date start, Date end) {
            this.start = start;
            this.end = end;
        }

        public Date getStart () {
            return start;
        }

        public Date getEnd(){
            return end;
        }
    }
}
