package web.app.moldunity.entity.transport.car;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import web.app.moldunity.entity.transport.Auto;

@Data
@Entity
@Table(name = "car")
public class Car extends Auto<CarImage, CarView> {
    @NotEmpty
    private String body;
}
