package web.app.moldunity.entity.electronics.televisor;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.appliances.AppliancesView;

@Data
@Entity
@Table(name = "televisor_views")
@EqualsAndHashCode(callSuper = true)
public class TelevisorView extends AppliancesView<Televisor> {
}
