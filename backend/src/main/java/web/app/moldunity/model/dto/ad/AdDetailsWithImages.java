package web.app.moldunity.model.dto.ad;

import com.fasterxml.jackson.annotation.JsonInclude;
import web.app.moldunity.model.entity.postgres.ad.Subcategory;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record AdDetailsWithImages(
        AdWithImages ad,
        Subcategory subcategory
) {}
