package web.app.moldunity.filter;

import org.springframework.stereotype.Component;
import web.app.moldunity.filter.transport.BusMinibusFilter;
import web.app.moldunity.filter.transport.CarFilter;

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
