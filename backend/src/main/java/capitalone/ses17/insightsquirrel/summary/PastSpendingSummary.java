package capitalone.ses17.insightsquirrel.summary;

import com.jayway.jsonpath.JsonPath;

import java.util.List;

public class PastSpendingSummary {

    public String category = null;
    public double total;
    public String fromDate;
    public String toDate;
    public String location;
    public int purchases;

    public PastSpendingSummary(String json, String fromDate, String toDate, String location, String category) {

        if (category != null) {
            this.category = category;
        }
        this.purchases = ((Integer) JsonPath.read(json, "$.hits.total")).intValue();
        this.total = ((Double) JsonPath.read(json, "$.aggregations.1.value")).doubleValue();
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.location = location;
    }
}
