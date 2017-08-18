package capitalone.ses17.insightsquirrel.summary;

import capitalone.ses17.insightsquirrel.elastic.ElasticController;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
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

    public Summary getPastSpending(double radius, String location,
                              String start, String end, String category) {

        LatLng loc = getCoordinates(location);
        String json = elasticController.getTimeLocationCategoy(start, end, loc.lat, loc.lng, category);

        // transform data
        Summary summary = new Summary(); //JsonHelper.jsonToTransactions(json));

        summary.category = JsonPath.read(json, "$.hits.hits[0]._source.merchant.category[0]").toString();
        summary.humanizedDate = "the last week";
        summary.total = ((Double) JsonPath.read(json, "$.hits.hits[0]._source.purchase.amount")).doubleValue();
        summary.fromDate = new Date(1499659200000L); // JsonPath.read(json, "$.transactions[0].purchase.purchase_date
        summary.toDate = new Date(1498881600000L);   // JsonPath.read(json, "$.transactions[0].purchase.purchase_date
        summary.location = location;

        return summary;
    }

    public Summary getFutureSpending(double radius, String name,
                              String start, String end, String category) {

        return null;
    }

    public Summary getSpendingAdvice(String start, String end) {
        String json = elasticController.getTimeLocationCategoy(null, null, 0, 0, "");

        return null;
    }

    /*
     * Textual coordinates to latitude and longitude
     */
    private LatLng getCoordinates (String location) {
        GeoApiContext context = new GeoApiContext.Builder()
                //.apiKey("YOUR-KEY-HERE")
                .build();

        GeocodingResult[] results = null;
        try {
            results = GeocodingApi.geocode(context, location).await();
        } catch (Exception e) {
            // yikes
        }

        return results[0].geometry.location;
    }

}
