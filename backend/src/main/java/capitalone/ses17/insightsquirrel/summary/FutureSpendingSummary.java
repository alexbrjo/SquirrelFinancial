package capitalone.ses17.insightsquirrel.summary;

import com.jayway.jsonpath.JsonPath;

public class FutureSpendingSummary {

    public String name = null;
    public double total;
    public String fromDate;
    public String toDate;
    public int purchases;

    public FutureSpendingSummary(String json, String fromDate, String toDate, String name) {

        if (name != null) {
            this.name = name;
        }
        this.purchases = ((Integer) JsonPath.read(json, "$.hits.total")).intValue();
        this.total = ((Double) JsonPath.read(json, "$.aggregations.1.value")).doubleValue();
        this.fromDate = fromDate;
        this.toDate = toDate;
    }
}
