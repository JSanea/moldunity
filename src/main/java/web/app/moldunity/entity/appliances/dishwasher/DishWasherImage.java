package web.app.moldunity.entity.appliances.dishwasher;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.appliances.AppliancesImage;

@Data
@Entity
@Table(name = "dishwasher_images")
public class DishWasherImage extends AppliancesImage<DishWasher> {
}
