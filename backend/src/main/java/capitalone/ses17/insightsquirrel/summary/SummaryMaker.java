package capitalone.ses17.insightsquirrel.summary;

import org.springframework.stereotype.Controller;
import sun.util.calendar.CalendarDate;

import java.util.List;

/**
 * Creates summaries of transactions to be consumed by the core API
 *
 * @author Alex Johnson
 */

@Controller
public class SummaryMaker {

    public Summary getSummary(double radius, double latitude, double longitude,
                              CalendarDate start, CalendarDate end, String category) {

        Summary s = new Summary();
        // build query for elastic search

        return null;
    }

}
