package web.app.moldunity.dto.ad;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record AdPage(
        List<AdWithImages> ads,
        Long total,
        Long page
){}
