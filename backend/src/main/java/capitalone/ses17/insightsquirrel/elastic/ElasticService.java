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

    public String DateLocationCategory(String fromDate, String toDate, double lon, double lat, String category) {

        String query = String.format("{\n" +
                        "  \"query\": {\n" +
                        "    \"bool\": {\n" +
                        "      \"must\": [\n" +
                        "        {\n" +
                        "          \"query_string\": { \"query\": \"merchant.category: %s\"}\n" +
                        "        },\n" +
                        "        {\n" +
                        "          \"geo_distance\": {\n" +
                        "            \"distance\": \"%dkm\",\n" +
                        "            \"geoip.location\": {\n" +
                        "              \"lat\": %f,\n" +
                        "              \"lon\": %f\n" +
                        "            }\n" +
                        "          }\n" +
                        "        },\n" +
                        "        {\n" +
                        "          \"range\": {\n" +
                        "            \"@timestamp\": {\n" +
                        "              \"gte\": \"%s\",\n" +
                        "              \"lte\": \"%s\",\n" +
                        "              \"format\": \"yyyy-MM-dd\"\n" +
                        "            }\n" +
                        "          }\n" +
                        "        }\n" +
                        "      ]\n" +
                        "    }\n" +
                        "  },\n" +
                        "  \"size\": 0,\n" +
                        "  \"_source\": {\n" +
                        "    \"excludes\": []\n" +
                        "  }," +
                        "  \"aggs\": {\n" +
                        "    \"1\": {\n" +
                        "      \"sum\": {\n" +
                        "        \"field\": \"purchase.amount\"\n" +
                        "      }\n" +
                        "    }\n" +
                        "  }\n" +
                        "}",
                category, 50, lat, lon, fromDate, toDate);
        try {
            HttpResponse<String> response = Unirest.post("http://ec2-52-55-165-133.compute-1.amazonaws.com:9200/logstash-squirrel-%2A/_search")
                    .header("content-type", "application/json")
                    .header("cache-control", "no-cache")
                    .body(query)
                    .asString();

            return response.getBody();
        } catch (UnirestException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String AverageTimeMerchant(String fromDate, String toDate, String merchant) {

        String query = String.format("{\n" +
                        "  \"query\": {\n" +
                        "    \"bool\": {\n" +
                        "      \"must\": [\n" +
                        "        {\n" +
                        "          \"query_string\": {\n" +
                        "            \"analyze_wildcard\": true,\n" +
                        "            \"query\": \"*\"\n" +
                        "          }\n" +
                        "        },\n" +
                        "        {\n" +
                        "          \"match_phrase\": {\n" +
                        "            \"merchant.name\": {\n" +
                        "              \"query\": \"%s\"\n" +
                        "            }\n" +
                        "          }\n" +
                        "        },\n" +
                        "        {\n" +
                        "          \"range\": {\n" +
                        "            \"@timestamp\": {\n" +
                        "              \"gte\": \"%s\",\n" +
                        "              \"lte\": \"%s\",\n" +
                        "              \"format\": \"yyyy-MM-dd\"\n" +
                        "            }\n" +
                        "          }\n" +
                        "        }\n" +
                        "      ],\n" +
                        "      \"must_not\": []\n" +
                        "    }\n" +
                        "  },\n" +
                        "  \"size\": 0,\n" +
                        "  \"_source\": {\n" +
                        "    \"excludes\": []\n" +
                        "  },\n" +
                        "  \"aggs\": {\n" +
                        "    \"1\": {\n" +
                        "      \"avg\": {\n" +
                        "        \"field\": \"purchase.amount\"\n" +
                        "      }\n" +
                        "    }\n" +
                        "  }\n" +
                        "}",
                merchant, fromDate, toDate);
        try {
            HttpResponse<String> response = Unirest.post("http://ec2-52-55-165-133.compute-1.amazonaws.com:9200/logstash-squirrel-%2A/_search")
                    .header("content-type", "application/json")
                    .header("cache-control", "no-cache")
                    .body(query)
                    .asString();

            return response.getBody();
        } catch (UnirestException e) {
            e.printStackTrace();
            return null;
        }
    }

}
