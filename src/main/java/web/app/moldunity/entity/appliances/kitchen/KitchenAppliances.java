package web.app.moldunity.entity.appliances.kitchen;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import web.app.moldunity.entity.appliances.AppliancesArticle;

@Data
@Entity
@Table(name = "kitchen_appliances")
public class KitchenAppliances extends AppliancesArticle<KitchenAppliancesComment, KitchenAppliancesImage, KitchenAppliancesView> {
    @NotEmpty
    private String brand;
}
