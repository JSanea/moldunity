package web.app.moldunity.entity.furniture.dining;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.furniture.FurnitureImage;

@Data
@Entity
@Table(name = "dining_images")
public class DiningArticleImage extends FurnitureImage<DiningArticle> {
}
