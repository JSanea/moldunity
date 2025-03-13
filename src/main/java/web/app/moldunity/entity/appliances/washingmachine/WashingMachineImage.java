package web.app.moldunity.entity.appliances.washingmachine;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.appliances.AppliancesImage;

@Data
@Entity
@Table(name = "washing_machine_images")
public class WashingMachineImage extends AppliancesImage<WashingMachine> {
}
