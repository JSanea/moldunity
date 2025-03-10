package web.app.moldunity.entity.furniture.kitchen;

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
@Table(name = "kitchen_comment_replies")
public class KitchenArticleCommentReply extends ArticleCommentReply {
    @ManyToOne
    @JoinColumn(name = "comment_id")
    private KitchenArticleComment comment;
}
