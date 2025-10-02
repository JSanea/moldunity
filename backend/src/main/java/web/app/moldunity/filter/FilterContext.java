package web.app.moldunity.filter;

import java.util.List;
import java.util.Map;


public record FilterContext(
        Map<String, List<String>> filters,
        StringBuilder whereSql,
        Map<String, Object> params
){}
