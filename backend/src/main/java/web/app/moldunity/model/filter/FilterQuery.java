package web.app.moldunity.model.filter;

import java.util.Map;

public record FilterQuery(
        String sql,
        String countSql,
        Map<String, Object> params
){}
