package web.app.moldunity.entity.furniture.office;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.furniture.FurnitureComment;

@Data
@Entity
@Table(name = "office_comments")
public class OfficeArticleComment extends FurnitureComment<OfficeArticle, OfficeArticleCommentReply> {
}
