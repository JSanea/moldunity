package web.app.moldunity.entity.appliances.oven;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.appliances.AppliancesView;

@Data
@Entity
@Table(name = "oven_views")
@EqualsAndHashCode(callSuper = true)
public class OvenView extends AppliancesView<Oven> {
}
