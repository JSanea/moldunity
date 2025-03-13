package web.app.moldunity.entity.appliances;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import web.app.moldunity.entity.article.ArticleCommentReply;

@Data
@MappedSuperclass
public abstract class AppliancesCommentReply <C> extends ArticleCommentReply {
    @ManyToOne
    @JoinColumn(name = "comment_id")
    private C comment;
}
