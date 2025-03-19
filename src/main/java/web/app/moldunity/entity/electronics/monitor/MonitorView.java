package web.app.moldunity.entity.electronics.monitor;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.electronics.ElectronicsView;

@Data
@Entity
@Table(name = "monitor_views")
@EqualsAndHashCode(callSuper = true)
public class MonitorView extends ElectronicsView<Monitor> {
}
