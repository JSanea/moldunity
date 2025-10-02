package web.app.moldunity.filter;

import java.util.List;

public class FilterHandler {
    public static void singleFilter(List<String> fields, String table, FilterContext ctx){
        for (String field : fields){
            List<String> values = ctx.filters().get(field);
            if (values != null && !values.isEmpty()) {
                String value = values.get(0);
                if (value != null && !value.trim().isEmpty() && !"null".equalsIgnoreCase(value.trim())) {
                    ctx.whereSql().append("AND ").append(table).append(".").append(field).append(" = :").append(field).append(" ");
                    ctx.params().put(field, value);
                }
            }
        }
    }

    public static void multipleFilter(List<String> fields, String table, FilterContext ctx){
        for(String field : fields){
            List<String> values = ctx.filters().get(field);
            if(values != null && !values.isEmpty()){
                values = values.stream()
                        .filter(v -> v != null && !v.trim().isEmpty() && !"null".equalsIgnoreCase(v.trim()))
                        .toList();
                if(!values.isEmpty()){
                    ctx.whereSql().append("AND ").append(table).append(".").append(field).append(" IN (:").append(field).append(") ");
                    ctx.params().put(field, values);
                }
            }
        }
    }

    public static void rangeFilter(List<String> fields, String table, FilterContext ctx) {
        for (String field : fields){
            String fieldMin = field + "_min";
            String fieldMax = field + "_max";
            List<String> min = ctx.filters().get(fieldMin);
            List<String> max = ctx.filters().get(fieldMax);

            if (min != null && !min.isEmpty()) {
                String value = min.get(0);
                if(value != null && !value.trim().isEmpty() && !"null".equalsIgnoreCase(value.trim())){
                    ctx.whereSql().append("AND ").append(table).append(".").append(field)
                            .append(" >= :").append(fieldMin).append(" ");
                    ctx.params().put(fieldMin, Integer.parseInt(value));
                }
            }

            if (max != null && !max.isEmpty()) {
                String value = max.get(0);
                if(value != null && !value.trim().isEmpty() && !"null".equalsIgnoreCase(value.trim())){
                    ctx.whereSql().append("AND ").append(table).append(".").append(field)
                            .append(" <= :").append(fieldMax).append(" ");
                    ctx.params().put(fieldMax, Integer.parseInt(value));
                }
            }
        }
    }

    public static void applyFilters(String table,
                                    FilterContext ctx,
                                    List<String> singleFields,
                                    List<String> multipleFields,
                                    List<String> rangeFields) {
        FilterHandler.singleFilter(singleFields, table, ctx);
        FilterHandler.multipleFilter(multipleFields, table, ctx);
        FilterHandler.rangeFilter(rangeFields, table, ctx);
    }
}
