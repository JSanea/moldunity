package web.app.moldunity.entity.electronics.computer;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.electronics.ElectronicsView;

@Data
@Entity
@Table(name = "computer_views")
@EqualsAndHashCode(callSuper = true)
public class ComputerView extends ElectronicsView<Computer> {
}
