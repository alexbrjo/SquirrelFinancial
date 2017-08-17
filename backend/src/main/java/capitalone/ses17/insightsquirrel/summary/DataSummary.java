package capitalone.ses17.insightsquirrel.summary;

import java.util.Date;

/**
 * Summary of a set of transaction data.
 *
 * @author Alex Johnson
 */
public interface DataSummary {

    public String getSummaryName();

    public DateRange getRangeStart();

    public DateRange getRangeEnd();

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
