package web.app.moldunity.entity.electronics.smartphone;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.electronics.ElectronicsImage;

@Data
@Entity
@Table(name = "smartphone_images")
@EqualsAndHashCode(callSuper = true)
public class SmartphoneImage extends ElectronicsImage<Smartphone> {
}
