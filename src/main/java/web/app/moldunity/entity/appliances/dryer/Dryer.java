package web.app.moldunity.entity.appliances.dryer;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import web.app.moldunity.entity.appliances.AppliancesArticle;

@Data
@Entity
@Table(name = "dryer")
public class Dryer extends AppliancesArticle<DryerComment, DryerImage, DryerView> {
    @NotEmpty
    private String brand;

    @NotEmpty
    private String capacity;

    @NotEmpty
    private String dryingType;

    @NotEmpty
    private String color;
}
