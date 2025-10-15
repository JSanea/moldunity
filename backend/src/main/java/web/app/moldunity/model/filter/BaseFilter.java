package web.app.moldunity.model.filter;

import web.app.moldunity.model.filter.ad.BaseAdFilter;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public abstract class BaseFilter {
    protected FilterQuery buildFilterQuery(String table,
                                           Map<String, List<String>> filters,
                                           Consumer<FilterContext> extraFilterLogic) {
        if (filters == null || filters.isEmpty()) {
            return new FilterQuery(
                    "WHERE ads.subcategory_name = :subcategory AND republished_at >= NOW() - INTERVAL '30 days' ORDER BY ads.republished_at DESC ",
                    "SELECT count(*) FROM ads WHERE subcategory_name = :subcategory AND republished_at >= NOW() - INTERVAL '30 days' ",
                    Collections.emptyMap());
        }

        String joinSql = "INNER JOIN " + table + " ON ads.id = " + table + ".ad_id ";
        String countSql = "SELECT count(ads.id) FROM ads ";
        StringBuilder whereSql = new StringBuilder("WHERE 1=1 ");
        Map<String, Object> params = new HashMap<>();

        FilterContext ctx = new FilterContext(filters, whereSql, params);

        BaseAdFilter.createFilter(ctx);

        extraFilterLogic.accept(ctx);

        whereSql.append("AND republished_at >= NOW() - INTERVAL '30 days' ");
        String sort = Sort.sortBy(filters.get("sort"));

        return new FilterQuery(
                joinSql + whereSql + sort,
                countSql + joinSql + whereSql,
                params
        );
    }
}
