package web.app.moldunity.entity.electronics.televisor;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.appliances.AppliancesImage;

@Data
@Entity
@Table(name = "televisor_images")
@EqualsAndHashCode(callSuper = true)
public class TelevisorImage extends AppliancesImage<Televisor> {
}
