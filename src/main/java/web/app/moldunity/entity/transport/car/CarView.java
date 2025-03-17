package web.app.moldunity.entity.transport.car;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.transport.TransportView;

@Data
@Entity
@Table(name = "car_views")
public class CarView extends TransportView<Car> {
}
