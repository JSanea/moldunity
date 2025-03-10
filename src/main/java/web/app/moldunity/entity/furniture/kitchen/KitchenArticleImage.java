package web.app.moldunity.entity.furniture.kitchen;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import web.app.moldunity.entity.article.ArticleImage;

@Getter
@Setter
@Entity
@Table(name = "kitchen_images")
public class KitchenArticleImage extends ArticleImage {
    @ManyToOne
    @JoinColumn(name = "article_id")
    private KitchenArticle article;
}
