package web.app.moldunity.model.dto.ad;

import com.fasterxml.jackson.annotation.JsonInclude;
import web.app.moldunity.model.entity.postgres.ad.Ad;
import web.app.moldunity.model.entity.postgres.ad.AdImage;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record AdWithImage(
        Ad ad,
        AdImage adImage
) {}
