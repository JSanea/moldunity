package web.app.moldunity.entity.appliances.cooker;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.appliances.AppliancesImage;

@Data
@Entity
@Table(name = "cooker_images")
@EqualsAndHashCode(callSuper = true)
public class CookerImage extends AppliancesImage<Cooker> {
}
