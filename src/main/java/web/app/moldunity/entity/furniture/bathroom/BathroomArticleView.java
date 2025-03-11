package web.app.moldunity.entity.furniture.bathroom;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import web.app.moldunity.entity.article.ArticleView;
import web.app.moldunity.entity.furniture.FurnitureView;

@Data
@Entity
@Table(name = "bathroom_views")
public class BathroomArticleView extends FurnitureView<BathroomArticle> {
}
