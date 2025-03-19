package web.app.moldunity.entity.transport.agri;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.transport.TransportView;

@Data
@Entity
@Table(name = "agri_vehicle_views")
@EqualsAndHashCode(callSuper = true)
public class AgriVehicleView extends TransportView<AgriVehicle> {
}
