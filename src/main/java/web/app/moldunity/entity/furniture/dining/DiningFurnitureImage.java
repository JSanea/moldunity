package web.app.moldunity.entity.furniture.dining;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.furniture.FurnitureImage;

@Data
@Entity
@Table(name = "dining_images")
@EqualsAndHashCode(callSuper = true)
public class DiningFurnitureImage extends FurnitureImage<DiningFurniture> {
}
