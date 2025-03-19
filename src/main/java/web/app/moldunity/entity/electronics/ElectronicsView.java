package web.app.moldunity.entity.electronics;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.article.ArticleView;

@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
public abstract class ElectronicsView<A> extends ArticleView {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "article_id")
    private A article;
}
