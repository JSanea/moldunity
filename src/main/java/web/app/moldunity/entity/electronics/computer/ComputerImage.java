package web.app.moldunity.entity.electronics.computer;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.electronics.ElectronicsImage;

@Data
@Entity
@Table(name = "computer_images")
@EqualsAndHashCode(callSuper = true)
public class ComputerImage extends ElectronicsImage<Computer> {
}
