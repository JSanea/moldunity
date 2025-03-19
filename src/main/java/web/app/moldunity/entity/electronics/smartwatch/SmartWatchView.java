package web.app.moldunity.entity.electronics.smartwatch;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.electronics.ElectronicsView;

@Data
@Entity
@Table(name = "smartwatch_views")
@EqualsAndHashCode(callSuper = true)
public class SmartWatchView extends ElectronicsView<SmartWatch> {
}
