package web.app.moldunity.entity.furniture.other;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.furniture.FurnitureImage;

@Data
@Entity
@Table(name = "other_furniture_images")
@EqualsAndHashCode(callSuper = true)
public class OtherFurnitureImage extends FurnitureImage<OtherFurniture> {
}
