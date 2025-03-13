package web.app.moldunity.entity.electronics.laptop_tablet;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.electronics.ElectronicsView;

@Data
@Entity
@Table(name = "laptop_tablet_views")
public class LaptopTabletView extends ElectronicsView<LaptopTablet> {
}
