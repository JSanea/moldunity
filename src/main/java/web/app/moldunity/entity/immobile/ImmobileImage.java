package web.app.moldunity.entity.immobile;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.article.ArticleImage;

@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
public abstract class ImmobileImage<A> extends ArticleImage {
    @ManyToOne
    @JoinColumn(name = "article_id")
    private A article;
}
