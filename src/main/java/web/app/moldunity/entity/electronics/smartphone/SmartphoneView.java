package web.app.moldunity.entity.electronics.smartphone;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.electronics.ElectronicsView;

@Data
@Entity
@Table(name = "smartphone_views")
public class SmartphoneView extends ElectronicsView<Smartphone> {
}
