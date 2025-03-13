package web.app.moldunity.entity.furniture.dining;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.furniture.FurnitureArticle;

@Data
@Entity
@Table(name = "dining")
public class DiningArticle extends FurnitureArticle<DiningArticleComment, DiningArticleImage, DiningArticleView> {
}
