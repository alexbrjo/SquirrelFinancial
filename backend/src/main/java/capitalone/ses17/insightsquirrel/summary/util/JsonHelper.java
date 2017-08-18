package capitalone.ses17.insightsquirrel.summary.util;

import capitalone.ses17.insightsquirrel.summary.SummaryMaker;
import capitalone.ses17.insightsquirrel.summary.model.Transaction;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import java.util.*;

/** Just to avoid compilation errors */
public class JsonHelper {

    private JsonHelper(){}

    public static List<Transaction> jsonToTransactions (String json){

        List<Transaction> transactions = new ArrayList<Transaction>();
        JSONArray hits = JsonPath.read(json, "$.hits.hits");

        for (Object j : hits) {
            transactions.add(jsonToTransaction((Map) j)); // scary but true
        }

        return transactions;
    }

    public static Transaction jsonToTransaction (Map json) {

        Transaction transcation = new Transaction();
        transcation.categories = Collections.singletonList(
                JsonPath.read(json, "_source.merchant.category[0]"));
        transcation.ammount = ((Double) JsonPath.read(json, "_source.purchase.amount")).doubleValue();
        //transcation.fromDate = JsonPath.read(json, "$.transactions[0].purchase.purchase_date");
        //transcation.toDate = JsonPath.read(json, "$.transactions[0].purchase.purchase_date");

        return transcation;
    }


    public static void main (String[] args) {
        JsonHelper.jsonToTransactions("{\n" +
                "    \"took\": 4,\n" +
                "    \"timed_out\": false,\n" +
                "    \"_shards\": {\n" +
                "        \"total\": 10,\n" +
                "        \"successful\": 10,\n" +
                "        \"failed\": 0\n" +
                "    },\n" +
                "    \"hits\": {\n" +
                "        \"total\": 2,\n" +
                "        \"max_score\": 1,\n" +
                "        \"hits\": [\n" +
                "            {\n" +
                "                \"_index\": \"logstash-squirrel-2017.08.13\",\n" +
                "                \"_type\": \"logs\",\n" +
                "                \"_id\": \"AV3yBG6cgPHO-7YEgPlg\",\n" +
                "                \"_score\": 1,\n" +
                "                \"_source\": {\n" +
                "                    \"geoip\": {\n" +
                "                        \"location\": {\n" +
                "                            \"lon\": \"-79.046\",\n" +
                "                            \"lat\": \"35.909\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"@timestamp\": \"2017-08-13T04:00:00.000Z\",\n" +
                "                    \"purchase\": {\n" +
                "                        \"amount\": 12.23,\n" +
                "                        \"description\": null,\n" +
                "                        \"payer_id\": \"5995a4b1a73e4942cdafdafb\",\n" +
                "                        \"_id\": \"5995b0a6ceb8abe2425184a3\",\n" +
                "                        \"merchant_id\": \"57cf75cea73e494d8675ec49\",\n" +
                "                        \"medium\": \"balance\",\n" +
                "                        \"payee_id\": \"57cf75cea73e494d8675ec49\",\n" +
                "                        \"purchase_date\": \"2017-08-13\",\n" +
                "                        \"type\": \"merchant\",\n" +
                "                        \"status\": \"executed\"\n" +
                "                    },\n" +
                "                    \"merchant\": {\n" +
                "                        \"address\": {\n" +
                "                            \"zip\": \"27514\",\n" +
                "                            \"city\": \"Chapel Hill\",\n" +
                "                            \"street_number\": \"5901\",\n" +
                "                            \"state\": \"NC\",\n" +
                "                            \"street_name\": \"South Rd\"\n" +
                "                        },\n" +
                "                        \"name\": \"Dunkin\",\n" +
                "                        \"geocode\": {\n" +
                "                            \"lng\": -79.046,\n" +
                "                            \"lat\": 35.909\n" +
                "                        },\n" +
                "                        \"_id\": \"57cf75cea73e494d8675ec49\",\n" +
                "                        \"category\": [\n" +
                "                            \"Food\"\n" +
                "                        ]\n" +
                "                    },\n" +
                "                    \"account\": {\n" +
                "                        \"balance\": 876,\n" +
                "                        \"nickname\": \"Merle's credit card\",\n" +
                "                        \"_id\": \"5995a4b1a73e4942cdafdafb\",\n" +
                "                        \"type\": \"Credit Card\",\n" +
                "                        \"customer_id\": \"5995a4b0a73e4942cdafdaf9\",\n" +
                "                        \"rewards\": 15649\n" +
                "                    }\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"_index\": \"logstash-squirrel-2017.08.14\",\n" +
                "                \"_type\": \"logs\",\n" +
                "                \"_id\": \"AV3yGDnDgPHO-7YEgP_-\",\n" +
                "                \"_score\": 1,\n" +
                "                \"_source\": {\n" +
                "                    \"geoip\": {\n" +
                "                        \"location\": {\n" +
                "                            \"lon\": \"-76.5074715\",\n" +
                "                            \"lat\": \"42.4394625\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"@timestamp\": \"2017-08-14T04:00:00.000Z\",\n" +
                "                    \"purchase\": {\n" +
                "                        \"amount\": 20.45,\n" +
                "                        \"description\": null,\n" +
                "                        \"payer_id\": \"5995a4b1a73e4942cdafdafb\",\n" +
                "                        \"_id\": \"5995b298ceb8abe2425184a7\",\n" +
                "                        \"merchant_id\": \"57cf75cea73e494d8675ec55\",\n" +
                "                        \"medium\": \"balance\",\n" +
                "                        \"payee_id\": \"57cf75cea73e494d8675ec55\",\n" +
                "                        \"purchase_date\": \"2017-08-14\",\n" +
                "                        \"type\": \"merchant\",\n" +
                "                        \"status\": \"executed\"\n" +
                "                    },\n" +
                "                    \"merchant\": {\n" +
                "                        \"address\": {\n" +
                "                            \"zip\": \"14850\",\n" +
                "                            \"street number\": \"526\",\n" +
                "                            \"city\": \"Ithaca\",\n" +
                "                            \"street name\": \"West State Street\",\n" +
                "                            \"state\": \"NY\"\n" +
                "                        },\n" +
                "                        \"name\": \"Saigon Kitchen\",\n" +
                "                        \"geocode\": {\n" +
                "                            \"lng\": -76.5074715,\n" +
                "                            \"lat\": 42.4394625\n" +
                "                        },\n" +
                "                        \"_id\": \"57cf75cea73e494d8675ec55\",\n" +
                "                        \"category\": [\n" +
                "                            \"restaurant\",\n" +
                "                            \"food\",\n" +
                "                            \"point_of_interest\",\n" +
                "                            \"establishment\"\n" +
                "                        ]\n" +
                "                    },\n" +
                "                    \"account\": {\n" +
                "                        \"balance\": 876,\n" +
                "                        \"nickname\": \"Merle's credit card\",\n" +
                "                        \"_id\": \"5995a4b1a73e4942cdafdafb\",\n" +
                "                        \"type\": \"Credit Card\",\n" +
                "                        \"customer_id\": \"5995a4b0a73e4942cdafdaf9\",\n" +
                "                        \"rewards\": 15649\n" +
                "                    }\n" +
                "                }\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}");
    }

}
