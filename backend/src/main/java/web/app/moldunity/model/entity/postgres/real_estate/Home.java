package web.app.moldunity.model.entity.postgres.real_estate;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import web.app.moldunity.model.entity.postgres.ad.Subcategory;

@Getter
@Setter
@Table(value = "houses")
public class Home implements Subcategory {
    @Id
    private Long id;
    @NotNull private Integer floors;
    private Long AdId;
}
