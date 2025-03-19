package web.app.moldunity.entity.transport.bus_minibus;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.transport.TransportImage;

@Data
@Entity
@Table(name = "bus_minibus_images")
@EqualsAndHashCode(callSuper = true)
public class BusMinibusImage extends TransportImage<BusMinibus> {
}
