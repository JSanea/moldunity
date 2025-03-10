package web.app.moldunity.entity.furniture.bathroom;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import web.app.moldunity.entity.article.ArticleCommentReply;

@Getter
@Setter
@Entity
@Table(name = "bathroom_comment_replies")
public class BathroomArticleCommentReply extends ArticleCommentReply {
    @ManyToOne
    @JoinColumn(name = "comment_id")
    private BathroomArticleComment comment;
}
