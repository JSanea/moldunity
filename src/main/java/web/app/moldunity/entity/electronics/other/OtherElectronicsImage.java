package web.app.moldunity.entity.electronics.other;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.electronics.ElectronicsImage;

@Data
@Entity
@Table(name = "other_electronics_images")
@EqualsAndHashCode(callSuper = true)
public class OtherElectronicsImage extends ElectronicsImage<OtherElectronics> {
}
