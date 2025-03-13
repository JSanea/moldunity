package web.app.moldunity.entity.appliances.dishwasher;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import web.app.moldunity.entity.appliances.AppliancesArticle;

@Data
@Entity
@Table(name = "dishwasher")
public class DishWasher extends AppliancesArticle<DishWasherComment, DishWasherImage, DishWasherView> {
    @NotEmpty
    private String brand;

    @NotEmpty
    private String capacity;

    @NotEmpty
    private String dryingType;
}
