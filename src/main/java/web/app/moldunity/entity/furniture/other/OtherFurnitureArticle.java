package web.app.moldunity.entity.furniture.other;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.article.Article;
import web.app.moldunity.entity.furniture.FurnitureArticle;

@Data
@Entity
@Table(name = "other_furniture")
public class OtherFurnitureArticle extends FurnitureArticle<OtherFurnitureArticleComment, OtherFurnitureArticleImage, OtherFurnitureArticleView> {
}
