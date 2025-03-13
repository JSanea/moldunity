package web.app.moldunity.entity.electronics.monitor;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.electronics.ElectronicsView;

@Data
@Entity
@Table(name = "monitor_views")
public class MonitorView extends ElectronicsView<Monitor> {
}
