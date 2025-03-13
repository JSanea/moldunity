package web.app.moldunity.entity.furniture.other;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.furniture.FurnitureCommentReply;

@Data
@Entity
@Table(name = "other_furniture_comment_replies")
public class OtherFurnitureArticleCommentReply extends FurnitureCommentReply<OtherFurnitureArticleComment> {
}
