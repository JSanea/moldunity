package web.app.moldunity.entity.electronics;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import web.app.moldunity.entity.article.ArticleView;

@Data
@MappedSuperclass
public abstract class ElectronicsView<A> extends ArticleView {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "article_id")
    private A article;
}
