package web.app.moldunity.entity.appliances.iron;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.appliances.AppliancesView;

@Data
@Entity
@Table(name = "iron_views")
@EqualsAndHashCode(callSuper = true)
public class IronView extends AppliancesView<Iron> {
}
