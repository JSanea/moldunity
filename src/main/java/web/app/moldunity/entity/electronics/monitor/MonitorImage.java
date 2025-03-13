package web.app.moldunity.entity.electronics.monitor;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.electronics.ElectronicsImage;

@Data
@Entity
@Table(name = "monitor_images")
public class MonitorImage extends ElectronicsImage<Monitor> {
}
