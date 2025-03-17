package web.app.moldunity.entity.electronics.smartwatch;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.electronics.ElectronicsImage;

@Data
@Entity
@Table(name = "smartwatch_images")
public class SmartWatchImage extends ElectronicsImage<SmartWatch> {
}
