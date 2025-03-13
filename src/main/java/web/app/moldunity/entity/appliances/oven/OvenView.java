package web.app.moldunity.entity.appliances.oven;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.appliances.AppliancesView;

@Data
@Entity
@Table(name = "oven_views")
public class OvenView extends AppliancesView<Oven> {
}
