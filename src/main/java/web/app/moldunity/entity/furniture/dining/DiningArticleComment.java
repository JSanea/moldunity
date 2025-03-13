package web.app.moldunity.entity.furniture.dining;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.furniture.FurnitureComment;

@Data
@Entity
@Table(name = "dining_comments")
public class DiningArticleComment extends FurnitureComment<DiningArticle, DiningArticleCommentReply> {
}
