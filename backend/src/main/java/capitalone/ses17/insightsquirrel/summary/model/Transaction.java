package capitalone.ses17.insightsquirrel.summary.model;

import sun.util.calendar.CalendarDate;

import java.util.Date;
import java.util.List;

/** Just to avoid compilation errors */
public class Transaction {

    public double ammount;
    public List<String> categories;
    public Date toDate;
    public Date fromDate;
    public double latitude;
    public double longitude;

    public double getAmmount () {
        return ammount;
    }

    public List<String> getCategories() {
        return null;
    }

    public CalendarDate getDate () {
        return null;
    }

    public double getMerchantLong () {
        return 0;
    }

    public double getMerchantLat () {
        return 0;
    }

    public String getMerchantId() {
        return "";
    }
}
