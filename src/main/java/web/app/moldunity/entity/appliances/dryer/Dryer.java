package web.app.moldunity.entity.appliances.dryer;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.appliances.AppliancesArticle;

@Data
@Entity
@Table(name = "dryer")
@EqualsAndHashCode(callSuper = true)
public class Dryer extends AppliancesArticle<DryerImage, DryerView> {
    @NotEmpty
    private String brand;

    @NotEmpty
    private String capacity;

    @NotEmpty
    private String dryingType;

    @NotEmpty
    private String color;
}
