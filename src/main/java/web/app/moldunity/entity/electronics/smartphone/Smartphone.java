package web.app.moldunity.entity.electronics.smartphone;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import web.app.moldunity.entity.electronics.ElectronicsArticle;

@Data
@Entity
@Table(name = "smartphone")
public class Smartphone extends ElectronicsArticle<SmartphoneComment, SmartphoneImage, SmartphoneView> {
    @NotEmpty
    private String brand;

    @NotEmpty
    private String model;

    @NotEmpty
    private String diagonal;

    @NotEmpty
    private String refreshRate;

    @NotEmpty
    private String ram;

    @NotEmpty
    private String memory;

    @NotEmpty
    private String frontCamera;

    @NotEmpty
    private String mainCamera;

    @NotEmpty
    private String batteryCapacity;

    @NotEmpty
    private String os;

    @NotEmpty
    private String color;
}
