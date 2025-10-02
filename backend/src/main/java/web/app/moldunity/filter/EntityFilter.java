package web.app.moldunity.filter;

import java.util.List;
import java.util.Map;

public interface EntityFilter {
    FilterQuery filter(Map<String, List<String>> filters);
}
