package capitalone.ses17.insightsquirrel.summary;

import capitalone.ses17.insightsquirrel.summary.model.Transaction;
import sun.util.calendar.CalendarDate;

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
