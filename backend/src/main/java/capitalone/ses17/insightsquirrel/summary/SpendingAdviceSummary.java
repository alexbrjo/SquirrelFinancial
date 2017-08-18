package capitalone.ses17.insightsquirrel.summary;

import capitalone.ses17.insightsquirrel.summary.model.Transaction;
import com.google.gson.JsonArray;
import com.jayway.jsonpath.JsonPath;

import java.util.HashMap;
import java.util.List;

public class SpendingAdviceSummary {

    public double total;
    public String fromDate;
    public String toDate;
    public int purchases;
    public String category;
    public HashMap distribution;

    public SpendingAdviceSummary(String json, String fromDate, String toDate) {

        this.purchases = ((Integer) JsonPath.read(json, "$.hits.total")).intValue();
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.distribution = new HashMap<>();
        this.total = 0;

        int length = ((Integer) JsonPath.read(json, "$.aggregations.2.buckets.length()")).intValue();
        for (int i = 0; i < length; i++) {
            String category = JsonPath.read(json, String.format("$.aggregations.2.buckets[%d].key", i));
            double value = ((Double) JsonPath.read(json, String.format("$.aggregations.2.buckets[%d].1.value", i))).doubleValue();
            this.distribution.put(category, value);
            this.total += value;
        }
    }
}
