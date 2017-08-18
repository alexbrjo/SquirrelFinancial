package capitalone.ses17.insightsquirrel.summary;

import capitalone.ses17.insightsquirrel.summary.model.Transaction;
import com.jayway.jsonpath.JsonPath;

import java.util.List;

public class SpendingAdviceSummary {

    public double total;
    public String fromDate;
    public String toDate;
    public int purchases;
    public String category;

    public SpendingAdviceSummary(String json, String fromDate, String toDate) {

        if (category != null) {
            this.category = category;
        }
        this.purchases = ((Integer) JsonPath.read(json, "$.hits.total")).intValue();
        this.total = ((Double) JsonPath.read(json, "$.aggregations.1.value")).doubleValue();
        this.fromDate = fromDate;
        this.toDate = toDate;
    }
}
