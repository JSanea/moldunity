package web.app.moldunity.entity.furniture.other;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.furniture.FurnitureComment;

@Data
@Entity
@Table(name = "other_furniture_comments")
public class OtherFurnitureArticleComment extends FurnitureComment<OtherFurnitureArticle, OtherFurnitureArticleCommentReply> {
}
