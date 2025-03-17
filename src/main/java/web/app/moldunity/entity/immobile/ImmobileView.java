package web.app.moldunity.entity.immobile;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import web.app.moldunity.entity.article.ArticleView;

@Data
@MappedSuperclass
public abstract class ImmobileView<A> extends ArticleView {
    @ManyToOne
    @JoinColumn(name = "article_id")
    private A article;
}
