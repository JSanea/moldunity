package web.app.moldunity.entity.appliances.kitchen;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.appliances.AppliancesImage;

@Data
@Entity
@Table(name = "kitchen_appliances_images")
public class KitchenAppliancesImage extends AppliancesImage<KitchenAppliances> {
}
