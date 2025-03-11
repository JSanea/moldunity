package web.app.moldunity.entity.furniture.bedroom;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.furniture.FurnitureImage;

@Data
@Entity
@Table(name = "bedroom_views")
public class BedroomArticleView extends FurnitureImage<BedroomArticle> {
}
