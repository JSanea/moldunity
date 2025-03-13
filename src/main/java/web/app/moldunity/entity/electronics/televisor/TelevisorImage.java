package web.app.moldunity.entity.electronics.televisor;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.appliances.AppliancesImage;

@Data
@Entity
@Table(name = "televisor_images")
public class TelevisorImage extends AppliancesImage<Televisor> {
}
