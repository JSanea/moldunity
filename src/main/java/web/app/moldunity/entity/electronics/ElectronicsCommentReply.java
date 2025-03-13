package web.app.moldunity.entity.electronics;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import web.app.moldunity.entity.article.ArticleCommentReply;

@Data
@MappedSuperclass
public abstract class ElectronicsCommentReply<C> extends ArticleCommentReply {
    @ManyToOne
    @JoinColumn(name = "comment_id")
    private C comment;
}
