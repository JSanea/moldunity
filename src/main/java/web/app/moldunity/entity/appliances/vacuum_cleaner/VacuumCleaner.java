package web.app.moldunity.entity.appliances.vacuum_cleaner;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import web.app.moldunity.entity.appliances.AppliancesArticle;

@Data
@Entity
@Table(name = "vacuum_cleaner")
public class VacuumCleaner extends AppliancesArticle<VacuumCleanerImage, VacuumCleanerView> {
    @NotEmpty
    private String brand;
}
