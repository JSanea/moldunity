package web.app.moldunity.entity.furniture.bathroom;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import web.app.moldunity.entity.article.ArticleView;

@Getter
@Setter
@Entity
@Table(name = "bathroom_views")
public class BathroomArticleView extends ArticleView {
    @ManyToOne
    @JoinColumn(name = "article_id")
    private BathroomArticle article;
}
