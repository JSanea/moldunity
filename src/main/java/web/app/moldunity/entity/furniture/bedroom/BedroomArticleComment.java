package web.app.moldunity.entity.furniture.bedroom;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.furniture.FurnitureComment;

@Data
@Entity
@Table(name = "bedroom_comments")
public class BedroomArticleComment extends FurnitureComment<BedroomArticle, BedroomArticleCommentReply> {
}
