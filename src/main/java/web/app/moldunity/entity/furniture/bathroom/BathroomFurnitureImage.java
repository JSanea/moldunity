package web.app.moldunity.entity.furniture.bathroom;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.furniture.FurnitureImage;

@Data
@Entity
@Table(name = "bathroom_images")
@EqualsAndHashCode(callSuper = true)
public class BathroomFurnitureImage extends FurnitureImage<BathroomFurniture> {

}
