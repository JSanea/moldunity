package web.app.moldunity.entity.furniture.bedroom;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.furniture.FurnitureImage;

@Data
@Entity
@Table(name = "bedroom_views")
@EqualsAndHashCode(callSuper = true)
public class BedroomFurnitureView extends FurnitureImage<BedroomFurniture> {
}
