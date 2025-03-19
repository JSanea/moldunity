package web.app.moldunity.entity.transport.agri;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.transport.TransportImage;

@Data
@Entity
@Table(name = "agri_vehicle_images")
@EqualsAndHashCode(callSuper = true)
public class AgriVehicleImage extends TransportImage<AgriVehicle> {
}
