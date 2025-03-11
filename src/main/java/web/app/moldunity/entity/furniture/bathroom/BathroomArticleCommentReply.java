package web.app.moldunity.entity.furniture.bathroom;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import web.app.moldunity.entity.article.ArticleCommentReply;
import web.app.moldunity.entity.furniture.FurnitureCommentReply;

@Data
@Entity
@Table(name = "bathroom_comment_replies")
public class BathroomArticleCommentReply extends FurnitureCommentReply<BathroomArticleComment> {
}
