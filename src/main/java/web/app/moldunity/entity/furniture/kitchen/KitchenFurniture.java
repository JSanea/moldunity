package web.app.moldunity.entity.furniture.kitchen;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.furniture.FurnitureArticle;


@Data
@Entity
@Table(name = "kitchen_furniture")
@EqualsAndHashCode(callSuper = true)
public class KitchenFurniture extends FurnitureArticle<KitchenFurnitureImage, KitchenFurnitureView> {

}
