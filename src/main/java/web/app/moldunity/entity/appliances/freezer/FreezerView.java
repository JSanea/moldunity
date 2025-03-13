package web.app.moldunity.entity.appliances.freezer;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.appliances.AppliancesView;

@Data
@Entity
@Table(name = "freezer_views")
public class FreezerView extends AppliancesView<Freezer> {
}
