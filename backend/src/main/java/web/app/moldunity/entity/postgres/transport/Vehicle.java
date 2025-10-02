package web.app.moldunity.entity.postgres.transport;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Vehicle {
    @NotBlank private String  brand;
    @NotBlank private String  model;
    @NotNull  private Integer year;
    @NotNull  private Integer mileage;
    @NotBlank private String  fuel;
    @NotBlank private String  gearBox;
    @NotNull  private Integer engineCapacity;
    @NotNull  private Integer power;
    @NotBlank private String  color;
    @NotBlank private String  steeringWheel;
}







