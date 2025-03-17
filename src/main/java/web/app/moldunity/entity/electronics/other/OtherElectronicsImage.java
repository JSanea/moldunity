package web.app.moldunity.entity.electronics.other;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.electronics.ElectronicsImage;

@Data
@Entity
@Table(name = "other_electronics_images")
public class OtherElectronicsImage extends ElectronicsImage<OtherElectronics> {
}
