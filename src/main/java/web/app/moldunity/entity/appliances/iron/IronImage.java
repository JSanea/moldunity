package web.app.moldunity.entity.appliances.iron;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.appliances.AppliancesImage;

@Data
@Entity
@Table(name = "iron_images")
public class IronImage extends AppliancesImage<Iron> {
}
