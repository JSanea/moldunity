package web.app.moldunity.entity.furniture.office;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.furniture.FurnitureCommentReply;

@Data
@Entity
@Table(name = "office_comment_replies")
public class OfficeArticleCommentReply extends FurnitureCommentReply<OfficeArticleComment> {
}
