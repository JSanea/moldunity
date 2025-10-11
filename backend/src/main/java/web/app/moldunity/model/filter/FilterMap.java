package web.app.moldunity.model.filter;

import org.springframework.stereotype.Component;
import web.app.moldunity.model.filter.transport.BusMinibusFilter;
import web.app.moldunity.model.filter.transport.CarFilter;

import java.util.Map;

@Component
public class FilterMap {
    private static final Map<String, EntityFilter> filterMap = Map.of(
            "Car", new CarFilter(),
            "Bus-Minibus", new BusMinibusFilter()
    );

    public EntityFilter getFilter(String key){
        return filterMap.get(key);
    }
}
