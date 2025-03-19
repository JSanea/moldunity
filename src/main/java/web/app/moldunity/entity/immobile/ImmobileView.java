package web.app.moldunity.entity.immobile;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.article.ArticleView;

@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
public abstract class ImmobileView<A> extends ArticleView {
    @ManyToOne
    @JoinColumn(name = "article_id")
    private A article;
}
