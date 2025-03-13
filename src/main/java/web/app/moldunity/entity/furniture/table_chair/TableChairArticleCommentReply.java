package web.app.moldunity.entity.furniture.table_chair;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.furniture.FurnitureCommentReply;

@Data
@Entity
@Table(name = "table_char_comment_replies")
public class TableChairArticleCommentReply extends FurnitureCommentReply<TableChairArticleComment> {
}
