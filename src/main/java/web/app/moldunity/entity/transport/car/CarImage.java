package web.app.moldunity.entity.transport.car;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.transport.TransportImage;

@Data
@Entity
@Table(name = "car_images")
@EqualsAndHashCode(callSuper = true)
public class CarImage extends TransportImage<Car> {
}
