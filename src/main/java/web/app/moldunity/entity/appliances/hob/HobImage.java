package web.app.moldunity.entity.appliances.hob;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.appliances.AppliancesImage;

@Data
@Entity
@Table(name = "hob_images")
public class HobImage extends AppliancesImage<Hob> {
}
