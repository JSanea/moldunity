package web.app.moldunity.entity.electronics.smartwatch;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.electronics.ElectronicsImage;

@Data
@Entity
@Table(name = "smartwatch_images")
@EqualsAndHashCode(callSuper = true)
public class SmartWatchImage extends ElectronicsImage<SmartWatch> {
}
