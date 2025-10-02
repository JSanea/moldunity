package web.app.moldunity.filter.transport;

import web.app.moldunity.filter.BaseFilter;
import web.app.moldunity.filter.EntityFilter;
import web.app.moldunity.filter.FilterHandler;
import web.app.moldunity.filter.FilterQuery;

import java.util.List;
import java.util.Map;

public class CarFilter extends BaseFilter implements EntityFilter {
    private static final String TABLE = "cars";
    private static final List<String> multipleFields = List.of("body");

    @Override
    public FilterQuery filter(Map<String, List<String>> filters) {
        return buildFilterQuery(TABLE, filters, ctx -> {
            VehicleFilter.createFilter(TABLE, ctx);
            FilterHandler.multipleFilter(multipleFields, TABLE, ctx);}
        );
    }
}
