package web.app.moldunity.entity.appliances.climate;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.appliances.AppliancesView;

@Data
@Entity
@Table(name = "climate_views")
public class ClimateView extends AppliancesView<Climate> {
}
