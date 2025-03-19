package web.app.moldunity.entity.transport.car;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.transport.Auto;

@Data
@Entity
@Table(name = "car")
@EqualsAndHashCode(callSuper = true)
public class Car extends Auto<CarImage, CarView> {
    @NotEmpty
    private String body;
}
