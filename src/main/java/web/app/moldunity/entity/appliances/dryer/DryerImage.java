package web.app.moldunity.entity.appliances.dryer;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.appliances.AppliancesImage;

@Data
@Entity
@Table(name = "dryer_images")
public class DryerImage extends AppliancesImage<Dryer> {
}
