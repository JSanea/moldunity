package web.app.moldunity.entity.furniture.kitchen;

import jakarta.persistence.*;
import lombok.Data;
import web.app.moldunity.entity.furniture.FurnitureArticle;


@Data
@Entity
@Table(name = "kitchen_furniture")
public class KitchenArticle extends FurnitureArticle<KitchenArticleComment, KitchenArticleImage, KitchenArticleView> {

}
