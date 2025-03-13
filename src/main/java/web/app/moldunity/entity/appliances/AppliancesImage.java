package web.app.moldunity.entity.appliances;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import web.app.moldunity.entity.article.ArticleImage;

@Data
@MappedSuperclass
public class AppliancesImage<A> extends ArticleImage {
    @ManyToOne
    @JoinColumn(name = "article_id")
    private A article;
}
