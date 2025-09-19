package web.app.webflux_moldunity.dto.ad;

import com.fasterxml.jackson.annotation.JsonInclude;
import web.app.webflux_moldunity.entity.postgres.ad.Subcategory;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record AdDetailsWithImages(
        AdWithImages ad,
        Subcategory subcategory
) {}
