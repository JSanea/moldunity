package web.app.moldunity.entity.immobile.apartament;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.immobile.ImmobileImage;

@Data
@Entity
@Table(name = "apartament_images")
public class ApartamentImage extends ImmobileImage<Apartament> {
}
