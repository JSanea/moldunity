package web.app.moldunity.entity.appliances.vacuum_cleaner;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.appliances.AppliancesImage;

@Data
@Entity
@Table(name = "vacuum_cleaner_images")
@EqualsAndHashCode(callSuper = true)
public class VacuumCleanerImage extends AppliancesImage<VacuumCleaner> {
}
