package web.app.moldunity.entity.furniture.kitchen;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.furniture.FurnitureImage;

@Data
@Entity
@Table(name = "kitchen_furniture_images")
@EqualsAndHashCode(callSuper = true)
public class KitchenFurnitureImage extends FurnitureImage<KitchenFurniture> {

}
