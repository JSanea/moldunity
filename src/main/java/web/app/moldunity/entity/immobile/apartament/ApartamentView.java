package web.app.moldunity.entity.immobile.apartament;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.immobile.ImmobileView;

@Data
@Entity
@Table(name = "apartament_views")
public class ApartamentView extends ImmobileView<Apartament> {
}
