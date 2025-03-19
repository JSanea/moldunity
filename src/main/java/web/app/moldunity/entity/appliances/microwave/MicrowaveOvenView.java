package web.app.moldunity.entity.appliances.microwave;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.appliances.AppliancesView;

@Data
@Entity
@Table(name = "microwave_oven_views")
public class MicrowaveOvenView extends AppliancesView<MicrowaveOven> {
}
