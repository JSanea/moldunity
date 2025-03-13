package web.app.moldunity.entity.appliances.cooker;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.appliances.AppliancesView;

@Data
@Entity
@Table(name = "cooker_viewss")
public class CookerView extends AppliancesView<Cooker> {
}
