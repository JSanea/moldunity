package web.app.moldunity.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import web.app.moldunity.dto.ad.AdWithImages;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record Profile(
    UserProfile profile,
    List<AdWithImages> ads
){}
