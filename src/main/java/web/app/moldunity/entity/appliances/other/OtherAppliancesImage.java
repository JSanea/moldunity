package web.app.moldunity.entity.appliances.other;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.appliances.AppliancesImage;

@Data
@Entity
@Table(name = "other_appliances_images")
public class OtherAppliancesImage extends AppliancesImage<OtherAppliances> {
}
