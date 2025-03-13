package web.app.moldunity.entity.appliances.freezer;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import web.app.moldunity.entity.appliances.AppliancesArticle;


@Data
@Entity
@Table(name = "freezer")
public class Freezer extends AppliancesArticle<FreezerComment, FreezerImage, FreezerView> {
    @NotEmpty
    private String brand;

    @NotEmpty
    private String defrostType;
}
