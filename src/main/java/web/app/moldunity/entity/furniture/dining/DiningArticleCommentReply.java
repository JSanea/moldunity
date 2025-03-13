package web.app.moldunity.entity.furniture.dining;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.furniture.FurnitureCommentReply;
import web.app.moldunity.entity.furniture.kitchen.KitchenArticleComment;

@Data
@Entity
@Table(name = "dining_comment_replies")
public class DiningArticleCommentReply extends FurnitureCommentReply<DiningArticleComment> {
}
