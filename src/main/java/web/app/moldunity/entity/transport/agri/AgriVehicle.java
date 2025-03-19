package web.app.moldunity.entity.transport.agri;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.transport.Transport;

@Data
@Entity
@Table(name = "agri_vehicle")
@EqualsAndHashCode(callSuper = true)
public class AgriVehicle extends Transport<AgriVehicleImage, AgriVehicleView> {
}
