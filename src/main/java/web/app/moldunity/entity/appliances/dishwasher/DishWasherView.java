package web.app.moldunity.entity.appliances.dishwasher;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.appliances.AppliancesView;

@Data
@Entity
@Table(name = "dishwasher_views")
public class DishWasherView extends AppliancesView<DishWasher> {
}
