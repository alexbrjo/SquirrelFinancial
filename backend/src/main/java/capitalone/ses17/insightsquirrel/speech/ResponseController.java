package capitalone.ses17.insightsquirrel.speech;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.*;

@RestController
public class ResponseController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    /*
    POST endpoint that receives JSON data and formulates a response to send to Alexa
     */
    @RequestMapping(value = "/response", method = RequestMethod.POST,
            consumes = "application/json")
    public String response(@RequestBody String payload) {
        return payload;
    }

}
