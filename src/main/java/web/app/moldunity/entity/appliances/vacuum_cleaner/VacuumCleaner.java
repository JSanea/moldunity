package web.app.moldunity.entity.appliances.vacuum_cleaner;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.appliances.AppliancesArticle;

@Data
@Entity
@Table(name = "vacuum_cleaner")
@EqualsAndHashCode(callSuper = true)
public class VacuumCleaner extends AppliancesArticle<VacuumCleanerImage, VacuumCleanerView> {
    @NotEmpty
    private String brand;
}
