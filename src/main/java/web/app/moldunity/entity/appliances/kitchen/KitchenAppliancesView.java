package web.app.moldunity.entity.appliances.kitchen;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.appliances.AppliancesView;

@Data
@Entity
@Table(name = "kitchen_appliances_views")
@EqualsAndHashCode(callSuper = true)
public class KitchenAppliancesView extends AppliancesView<KitchenAppliances> {
}
