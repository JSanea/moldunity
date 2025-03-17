package web.app.moldunity.entity.immobile.home;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.immobile.ImmobileImage;

@Data
@Entity
@Table(name = "home_images")
public class HomeImage extends ImmobileImage<Home> {
}
