package capitalone.ses17.insightsquirrel.summary;

import capitalone.ses17.insightsquirrel.elastic.ElasticController;
import capitalone.ses17.insightsquirrel.summary.model.Transaction;
import com.google.gson.Gson;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.jayway.jsonpath.JsonPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Date;

/**
 * Creates summaries of transactions to be consumed by the core API
 *
 * @author Alex Johnson
 */
@Controller
public class SummaryMaker {

    @Autowired
    private ElasticController elasticController;

    public Summary getSummary(double radius, String location,
                              String start, String end, String category) {

        GeoApiContext context = new GeoApiContext.Builder()
                //.apiKey("YOUR-KEY-HERE")
                .build();

        GeocodingResult[] results = null;
        try {
            results = GeocodingApi.geocode(context, location).await();
        } catch (Exception e) {
            // yikes
        }

        double latitude = 35.909;   // results[0].geometry.location.lat;
        double longitude = -79.046; // results[0].geometry.location.lng;

        String json = elasticController.getTimeLocationCategoy(start, end, latitude, longitude, category);

        // transform data
        Summary summary = new Summary();

        summary.category = JsonPath.read(json, "$.hits.hits[0]._source.merchant.category[0]").toString();
        summary.humanizedDate = "the last week";
        summary.total = ((Double) JsonPath.read(json, "$.hits.hits[0]._source.purchase.amount")).doubleValue();
        summary.fromDate = new Date(1499659200000L); // JsonPath.read(json, "$.transactions[0].purchase.purchase_date
        summary.toDate = new Date(1498881600000L);   // JsonPath.read(json, "$.transactions[0].purchase.purchase_date
        summary.location = location;

        return summary;
    }

    public static void main(String[] args) {
        SummaryMaker s = new SummaryMaker();
    }

}
