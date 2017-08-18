package capitalone.ses17.insightsquirrel.summary;

import capitalone.ses17.insightsquirrel.elastic.ElasticController;
import capitalone.ses17.insightsquirrel.summary.util.JsonHelper;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Creates summaries of transactions to be consumed by the core API
 *
 * @author Alex Johnson
 */
@Controller
public class SummaryMaker {

    @Autowired
    private ElasticController elasticController;

    public PastSpendingSummary getPastSpending(double radius, String location,
                                        String fromDate, String toDate, String category) {

        LatLng loc = getCoordinates(location);
        String json = elasticController.getTimeLocationCategoy(fromDate, toDate, loc.lng, loc.lat, category);
        return new PastSpendingSummary(json, fromDate, toDate, location, category);
    }

    public FurtureSpendingSummary getFutureSpending(String name, String fromDate, String toDate) {

        String json = elasticController.getAverageTimeCategory(null, null, name);
        return new FurtureSpendingSummary(json);
    }

    public SpendingAdviceSummary getSpendingAdvice(String fromDate, String toDate) {

        String json = ""; // elasticController.getSpendingProfile()
        return new SpendingAdviceSummary(json);
    }

    /*
     * Textual coordinates to latitude and longitude
     */
    private LatLng getCoordinates (String location) {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyBj5_PdNi5fYpqUTQPVKgeSv965oXrca70")
                .build();

        GeocodingResult[] results = null;
        try {
            results = GeocodingApi.geocode(context, location).await();
        } catch (Exception e) {
            // yikes
        }

        System.out.println(results[0].geometry.location);

        return results[0].geometry.location;
    }

}
