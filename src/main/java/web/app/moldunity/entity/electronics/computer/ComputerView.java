package web.app.moldunity.entity.electronics.computer;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.electronics.ElectronicsView;

@Data
@Entity
@Table(name = "computer_views")
public class ComputerView extends ElectronicsView<Computer> {
}
