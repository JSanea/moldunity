package web.app.moldunity.entity.furniture.wardrobe;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.furniture.FurnitureCommentReply;

@Data
@Entity
@Table(name = "wardrobe_comment_replies")
public class WardrobeCommentReply extends FurnitureCommentReply<WardrobeComment> {
}
