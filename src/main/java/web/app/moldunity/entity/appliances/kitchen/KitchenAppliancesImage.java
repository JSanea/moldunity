package web.app.moldunity.entity.appliances.kitchen;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.appliances.AppliancesImage;

@Data
@Entity
@Table(name = "kitchen_appliances_images")
@EqualsAndHashCode(callSuper = true)
public class KitchenAppliancesImage extends AppliancesImage<KitchenAppliances> {
}
