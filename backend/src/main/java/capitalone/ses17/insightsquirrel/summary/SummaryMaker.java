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
                                        String start, String end, String category) {

        LatLng loc = getCoordinates(location);
        String json = elasticController.getTimeLocationCategoy(start, end, loc.lat, loc.lng, category);
        return new PastSpendingSummary(json);
    }

    public FurtureSpendingSummary getFutureSpending(String name, String start, String end) {

        String json = elasticController.getAverageTimeCategory(null, null, name);
        return new FurtureSpendingSummary(json);

    }

    public SpendingAdviceSummary getSpendingAdvice(String start, String end) {

        String json = ""; // elasticController.getSpendingProfile()
        return new SpendingAdviceSummary(json);
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
