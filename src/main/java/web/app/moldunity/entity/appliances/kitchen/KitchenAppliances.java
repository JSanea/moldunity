package web.app.moldunity.entity.appliances.kitchen;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.appliances.AppliancesArticle;

@Data
@Entity
@Table(name = "kitchen_appliances")
@EqualsAndHashCode(callSuper = true)
public class KitchenAppliances extends AppliancesArticle<KitchenAppliancesImage, KitchenAppliancesView> {
    @NotEmpty
    private String brand;
}
