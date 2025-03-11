package web.app.moldunity.entity.furniture.kitchen;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.furniture.FurnitureCommentReply;

@Data
@Entity
@Table(name = "kitchen_comment_replies")
public class KitchenArticleCommentReply extends FurnitureCommentReply<KitchenArticleComment> {
}
