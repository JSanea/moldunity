package web.app.moldunity.entity.transport.truck;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.transport.TransportView;

@Data
@Entity
@Table(name = "truck_views")
@EqualsAndHashCode(callSuper = true)
public class TruckView extends TransportView<Truck> {
}
