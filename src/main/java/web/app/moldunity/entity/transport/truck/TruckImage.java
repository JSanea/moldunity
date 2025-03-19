package web.app.moldunity.entity.transport.truck;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.transport.TransportImage;

@Data
@Entity
@Table(name = "truck_images")
@EqualsAndHashCode(callSuper = true)
public class TruckImage extends TransportImage<Truck> {
}
