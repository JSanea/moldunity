package web.app.moldunity.entity.electronics.laptop_tablet;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.electronics.ElectronicsImage;

@Data
@Entity
@Table(name = "laptop_tablet_images")
@EqualsAndHashCode(callSuper = true)
public class LaptopTabletImage extends ElectronicsImage<LaptopTablet> {
}
