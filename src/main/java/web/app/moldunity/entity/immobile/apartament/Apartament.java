package web.app.moldunity.entity.immobile.apartament;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.immobile.Housing;

@Data
@Entity
@Table(name = "apartament")
@EqualsAndHashCode(callSuper = true)
public class Apartament extends Housing {
    @NotEmpty
    private String floor;
}
