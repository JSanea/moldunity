package web.app.moldunity.entity.electronics.computer;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.electronics.ElectronicsImage;

@Data
@Entity
@Table(name = "computer_images")
public class ComputerImage extends ElectronicsImage<Computer> {
}
