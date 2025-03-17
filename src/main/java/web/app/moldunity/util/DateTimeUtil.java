package web.app.moldunity.util;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final String DATE_TIME_PATTERN = "dd-MM-yyyy HH:mm";

    private DateTimeUtil(){}

    public static LocalDateTime getDateTimeNow(){
        var formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
        return LocalDateTime.parse(LocalDateTime.now().format(formatter), formatter);
    }
}
