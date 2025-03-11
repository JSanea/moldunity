package web.app.moldunity.entity.furniture;

import jakarta.persistence.*;
import lombok.Data;
import web.app.moldunity.entity.article.ArticleComment;
import web.app.moldunity.entity.furniture.bathroom.BathroomArticle;
import web.app.moldunity.entity.furniture.bathroom.BathroomArticleCommentReply;
import web.app.moldunity.entity.furniture.bedroom.BedroomArticleCommentReply;

import java.util.List;

@Data
@MappedSuperclass
public abstract class FurnitureComment<A, R> extends ArticleComment {
    @OneToMany(mappedBy = "comment", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<R> replies;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private A article;
}
