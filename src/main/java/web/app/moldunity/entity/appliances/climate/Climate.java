package web.app.moldunity.entity.appliances.climate;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import web.app.moldunity.entity.appliances.AppliancesArticle;

@Data
@Entity
@Table(name = "climate")
public class Climate extends AppliancesArticle<ClimateComment, ClimateImage, ClimateView> {
    @NotEmpty
    private String brand;
}
