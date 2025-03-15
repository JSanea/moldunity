package web.app.moldunity.entity.electronics.laptop_tablet;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import web.app.moldunity.entity.electronics.ElectronicsArticle;

@Data
@Entity
@Table(name = "laptop_tablet")
public class LaptopTablet extends ElectronicsArticle<LaptopTabletImage, LaptopTabletView> {
    @NotEmpty
    private String diagonal;

    @NotEmpty
    private String processor;

    @NotEmpty
    private String ram;

    @NotEmpty
    private String memory;

    @NotEmpty
    private String videoCard;

    @NotEmpty
    private String storageType;

    @NotEmpty
    private String os;

    @NotEmpty
    private String batteryCapacity;

    @NotEmpty
    private String color;
}




























