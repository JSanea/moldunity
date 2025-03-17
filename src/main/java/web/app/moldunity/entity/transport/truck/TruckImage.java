package web.app.moldunity.entity.transport.truck;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.transport.TransportImage;

@Data
@Entity
@Table(name = "truck_images")
public class TruckImage extends TransportImage<Truck> {
}
