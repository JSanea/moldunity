package web.app.moldunity.entity.immobile.apartament;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import web.app.moldunity.entity.immobile.Housing;

@Data
@Entity
@Table(name = "apartament")
public class Apartament extends Housing<ApartamentImage, ApartamentView> {
    @NotEmpty
    private String floor;
}
