package web.app.moldunity.entity.appliances.washing_machine;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import web.app.moldunity.entity.appliances.AppliancesArticle;

@Data
@Entity
@Table(name = "washing_machine")
public class WashingMachine extends AppliancesArticle<WashingMachineComment, WashingMachineImage, WashingMachineView> {
    @NotEmpty
    private String brand;

    @NotEmpty
    private String type;

    @NotEmpty
    private String capacity;

    @NotEmpty
    private String rpm;

    @NotEmpty
    private String color;
}
