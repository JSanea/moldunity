package web.app.moldunity.entity.electronics.laptop_tablet;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import web.app.moldunity.entity.Advert;

@Entity
@Getter
@Setter
@Table(name = "laptops_tablets")
public class LaptopTabletAdvert extends Advert {
    @NotEmpty
    private String brand;
    @NotEmpty
    private String processor;
    @NotEmpty
    private String memory;
    @NotEmpty
    private String ram;
    @NotEmpty
    private String screenSize;
    @NotEmpty
    private String os;
}
