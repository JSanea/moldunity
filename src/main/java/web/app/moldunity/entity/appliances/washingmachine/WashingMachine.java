package web.app.moldunity.entity.appliances.washingmachine;

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
    private Byte capacity;

    @NotEmpty
    private Integer rpm;

    @NotEmpty
    private String color;
}
