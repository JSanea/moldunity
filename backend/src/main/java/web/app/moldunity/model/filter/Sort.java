package web.app.moldunity.model.filter;

import java.util.List;

public class Sort {
    public static String sortBy(List<String> sortList){
        if (sortList == null || sortList.isEmpty()) return "ORDER BY ads.republished_at DESC ";

        String raw = sortList.get(0);
        if (raw == null) return "ORDER BY ads.republished_at DESC ";

        String s = raw.trim().toLowerCase();
        if (s.isEmpty() || "null".equals(s)) return "ORDER BY ads.republished_at DESC ";

        return switch (s) {
            case "price_asc" -> "ORDER BY ads.price ASC ";
            case "price_desc" -> "ORDER BY ads.price DESC ";
            case "datetime_asc" -> "ORDER BY ads.republished_at ASC ";
            default -> "ORDER BY ads.republished_at DESC ";
        };
    }
}
