package web.app.moldunity.entity.furniture.kitchen;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.furniture.FurnitureImage;

@Data
@Entity
@Table(name = "kitchen_furniture_images")
public class KitchenArticleImage extends FurnitureImage<KitchenArticle> {

}
