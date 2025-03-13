package web.app.moldunity.entity.appliances.kitchen;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.appliances.AppliancesView;

@Data
@Entity
@Table(name = "kitchen_appliances_views")
public class KitchenAppliancesView extends AppliancesView<KitchenAppliances> {
}
