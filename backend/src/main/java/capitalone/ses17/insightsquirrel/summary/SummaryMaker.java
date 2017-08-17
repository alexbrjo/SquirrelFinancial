package capitalone.ses17.insightsquirrel.summary;

import capitalone.ses17.insightsquirrel.elastic.ElasticController;
import capitalone.ses17.insightsquirrel.summary.model.Transaction;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import sun.util.calendar.CalendarDate;

import java.util.Date;
import java.util.List;

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
                              Date start, Date end, String category) {

       /* GeoApiContext context = new GeoApiContext.Builder()
                //.apiKey("YOUR-KEY-HERE")
                .build();

        GeocodingResult[] results = null;
        try {
            results = GeocodingApi.geocode(context, location).await();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            System.out.println(gson.toJson(results[0].addressComponents));
        } catch (Exception e) {

        }

        double latitude = results[0].geometry.location.lat;
        double longitude = results[0].geometry.location.lng;

        String json = elasticController.getTimeLocationCategoy(start, end, latitude, longitude, category);

        // parse json
        Gson gson = new Gson();
        Transaction transaction = gson.fromJson(json, Transaction.class);

        // transform data
*/
        Summary summary = new Summary();

        summary.category = "food";
        summary.humanizedDate = "the last week";
        summary.total = 500;
        summary.fromDate = new Date(1499659200000L);
        summary.toDate = new Date(1498881600000L);
        summary.location = "Arlington";

        return summary;
    }



}
