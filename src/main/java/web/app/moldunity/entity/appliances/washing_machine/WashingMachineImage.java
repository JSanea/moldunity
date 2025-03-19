package web.app.moldunity.entity.appliances.washing_machine;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.appliances.AppliancesImage;

@Data
@Entity
@Table(name = "washing_machine_images")
@EqualsAndHashCode(callSuper = true)
public class WashingMachineImage extends AppliancesImage<WashingMachine> {
}
