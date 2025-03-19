package web.app.moldunity.entity.appliances.washing_machine;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.appliances.AppliancesView;

@Data
@Entity
@Table(name = "washing_machine_views")
@EqualsAndHashCode(callSuper = true)
public class WashingMachineView extends AppliancesView<WashingMachine> {
}
