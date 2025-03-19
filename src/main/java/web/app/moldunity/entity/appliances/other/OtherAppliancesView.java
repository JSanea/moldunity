package web.app.moldunity.entity.appliances.other;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.appliances.AppliancesView;

@Data
@Entity
@Table(name = "other_appliances_views")
@EqualsAndHashCode(callSuper = true)
public class OtherAppliancesView extends AppliancesView<OtherAppliances> {
}
