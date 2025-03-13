package web.app.moldunity.entity.appliances.other;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.appliances.AppliancesView;

@Data
@Entity
@Table(name = "other_appliances_views")
public class OtherAppliancesView extends AppliancesView<OtherAppliances> {
}
