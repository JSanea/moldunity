package web.app.moldunity.entity.appliances.dryer;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.appliances.AppliancesView;

@Data
@Entity
@Table(name = "dryer_views")
@EqualsAndHashCode(callSuper = true)
public class DryerView extends AppliancesView<Dryer> {
}
