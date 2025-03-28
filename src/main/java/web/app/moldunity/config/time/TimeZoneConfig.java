package web.app.moldunity.config.time;


import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.TimeZone;

@Component
public class TimeZoneConfig {
    private final String TIME_ZONE = "Europe/Chisinau";

    @PostConstruct
    public void init(){
        TimeZone.setDefault(TimeZone.getTimeZone(TIME_ZONE));
    }
}
