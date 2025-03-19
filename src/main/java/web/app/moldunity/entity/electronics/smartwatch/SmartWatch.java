package web.app.moldunity.entity.electronics.smartwatch;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.electronics.ElectronicsArticle;

@Data
@Entity
@Table(name = "smartwatch")
@EqualsAndHashCode(callSuper = true)
public class SmartWatch  extends ElectronicsArticle<SmartWatchImage, SmartWatchView> {
    @NotEmpty
    private String diagonal;

    @NotEmpty
    private String memory;

    @NotEmpty
    private String batteryCapacity;

    @NotEmpty
    private String os;

    @NotEmpty
    private String color;
}
