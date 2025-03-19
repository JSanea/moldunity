package web.app.moldunity.entity.appliances.microwave;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.appliances.AppliancesImage;

@Data
@Entity
@Table(name = "microwave_oven_images")
public class MicrowaveOvenImage extends AppliancesImage<MicrowaveOven> {
}
