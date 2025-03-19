package web.app.moldunity.entity.appliances.dryer;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.appliances.AppliancesImage;

@Data
@Entity
@Table(name = "dryer_images")
@EqualsAndHashCode(callSuper = true)
public class DryerImage extends AppliancesImage<Dryer> {
}
