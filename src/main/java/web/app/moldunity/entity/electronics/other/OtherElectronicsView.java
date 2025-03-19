package web.app.moldunity.entity.electronics.other;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.electronics.ElectronicsView;

@Data
@Entity
@Table(name = "other_electronics_views")
@EqualsAndHashCode(callSuper = true)
public class OtherElectronicsView extends ElectronicsView<OtherElectronics> {
}
