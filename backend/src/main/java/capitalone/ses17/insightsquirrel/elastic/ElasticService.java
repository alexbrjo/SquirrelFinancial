package capitalone.ses17.insightsquirrel.elastic;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by shane on 8/17/17.
 */

@Service
public class ElasticService {

    public String DateLocationCategory(Date fromDate, Date toDate, double lon, double lat, String category) {
        try {
            HttpResponse<String> response = Unirest.post("http://ec2-52-55-165-133.compute-1.amazonaws.com:9200/logstash-squirrel-%2A/_search")
                    .header("content-type", "application/json")
                    .header("cache-control", "no-cache")
                    .header("postman-token", "bdf1c1ea-f7f9-27ac-cc29-5af0ca95ca8f")
                    .body("{\n  \"query\": {\n    \"bool\": {\n      \"must\": [\n        {\n          \"query_string\": { \"query\": \"merchant.category: Food\"}\n        },\n        {\n          \"geo_distance\": {\n            \"distance\": \"3000km\",\n            \"geoip.location\": {\n              \"lat\": 38.878337,\n              \"lon\": -77.100703\n            }\n          }\n        },\n        {\n          \"range\": {\n            \"@timestamp\": {\n              \"gte\": \"2017/08/01\",\n              \"lte\": \"2017/08/14\",\n              \"format\": \"yyyy/MM/dd\"\n            }\n          }\n        }\n      ]\n    }\n  }\n}")
                    .asString();
            return response.toString();
        } catch (UnirestException e) {
            e.printStackTrace();
            return null;
        }
    }

}
