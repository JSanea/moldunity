package web.app.moldunity.entity.appliances.vacuum_cleaner;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.appliances.AppliancesView;

@Data
@Entity
@Table(name = "vacuum_cleaner_views")
public class VacuumCleanerView extends AppliancesView<VacuumCleaner> {
}
