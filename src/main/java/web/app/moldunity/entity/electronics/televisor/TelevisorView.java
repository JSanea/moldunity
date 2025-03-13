package web.app.moldunity.entity.electronics.televisor;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.appliances.AppliancesView;

@Data
@Entity
@Table(name = "televisor_views")
public class TelevisorView extends AppliancesView<Televisor> {
}
