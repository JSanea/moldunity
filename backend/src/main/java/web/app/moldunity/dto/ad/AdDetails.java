package web.app.moldunity.dto.ad;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
import web.app.moldunity.entity.postgres.ad.Ad;
import web.app.moldunity.entity.postgres.ad.Subcategory;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record AdDetails(
   @Valid Ad ad,
   @Valid Subcategory subcategory
){}
