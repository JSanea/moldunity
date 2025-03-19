package web.app.moldunity.entity.transport.car;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.transport.TransportView;

@Data
@Entity
@Table(name = "car_views")
@EqualsAndHashCode(callSuper = true)
public class CarView extends TransportView<Car> {
}
