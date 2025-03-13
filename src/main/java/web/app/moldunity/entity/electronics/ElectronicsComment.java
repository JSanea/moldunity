package web.app.moldunity.entity.electronics;

import jakarta.persistence.*;
import lombok.Data;
import web.app.moldunity.entity.article.ArticleComment;

import java.util.List;

@Data
@MappedSuperclass
public abstract class ElectronicsComment<A, R> extends ArticleComment {
    @OneToMany(mappedBy = "comment", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<R> replies;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private A article;
}
