package capitalone.ses17.insightsquirrel.speech;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResponseController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/response")
    public String response(@RequestParam(value="name", defaultValue="World") String name) {
        String str = "null";
        return str;
    }

}
