package web.app.moldunity.entity.appliances.dishwasher;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.appliances.AppliancesArticle;

@Data
@Entity
@Table(name = "dishwasher")
@EqualsAndHashCode(callSuper = true)
public class DishWasher extends AppliancesArticle<DishWasherImage, DishWasherView> {
    @NotEmpty
    private String brand;

    @NotEmpty
    private String capacity;

    @NotEmpty
    private String dryingType;
}
