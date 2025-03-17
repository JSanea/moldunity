package web.app.moldunity.entity.transport.bus_minibus;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import web.app.moldunity.entity.transport.Auto;

@Data
@Entity
@Table(name = "bus_minibus")
public class BusMinibus extends Auto<BusMinibusImage, BusMinibusView> {
    @NotEmpty
    private String numSeats;

    @NotEmpty
    private String body;
}












