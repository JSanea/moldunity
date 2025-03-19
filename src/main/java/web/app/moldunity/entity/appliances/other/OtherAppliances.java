package web.app.moldunity.entity.appliances.other;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.appliances.AppliancesArticle;

@Data
@Entity
@Table(name = "other_appliances")
@EqualsAndHashCode(callSuper = true)
public class OtherAppliances extends AppliancesArticle<OtherAppliancesImage, OtherAppliancesView> {
    @NotEmpty
    private String brand;
}
