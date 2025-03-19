package web.app.moldunity.entity.transport.truck;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.transport.Auto;

@Data
@Entity
@Table(name = "truck")
@EqualsAndHashCode(callSuper = true)
public class Truck extends Auto<TruckImage, TruckView> {
    @NotEmpty
    private String loadCapacity;
}
