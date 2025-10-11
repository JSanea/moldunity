package web.app.moldunity.model.dto.ad;

import com.fasterxml.jackson.annotation.JsonInclude;
import web.app.moldunity.model.entity.postgres.ad.Ad;
import web.app.moldunity.model.entity.postgres.ad.AdImage;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record AdWithImages(
        Ad ad,
        List<AdImage> adImages,
        Boolean isFavorite
){}
