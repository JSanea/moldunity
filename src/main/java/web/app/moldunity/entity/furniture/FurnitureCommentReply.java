package web.app.moldunity.entity.furniture;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import web.app.moldunity.entity.article.ArticleCommentReply;
import web.app.moldunity.entity.furniture.bathroom.BathroomArticleComment;

@Data
@MappedSuperclass
public abstract class FurnitureCommentReply <C> extends ArticleCommentReply {
    @ManyToOne
    @JoinColumn(name = "comment_id")
    private C comment;
}
