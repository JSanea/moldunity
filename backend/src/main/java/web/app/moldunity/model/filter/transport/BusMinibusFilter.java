package web.app.moldunity.model.filter.transport;


import web.app.moldunity.model.filter.BaseFilter;
import web.app.moldunity.model.filter.EntityFilter;
import web.app.moldunity.model.filter.FilterHandler;
import web.app.moldunity.model.filter.FilterQuery;

import java.util.List;
import java.util.Map;

public class BusMinibusFilter extends BaseFilter implements EntityFilter {
    private static final String TABLE = "buses_minibuses";
    private static final List<String> rangeFields = List.of("num_seats");

    @Override
    public FilterQuery filter(Map<String, List<String>> filters) {
        return buildFilterQuery(TABLE, filters, ctx -> {
            VehicleFilter.createFilter(TABLE, ctx);
            FilterHandler.rangeFilter(rangeFields, TABLE, ctx);
        });
    }
}
