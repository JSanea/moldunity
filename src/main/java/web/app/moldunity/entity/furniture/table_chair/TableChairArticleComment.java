package web.app.moldunity.entity.furniture.table_chair;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.furniture.FurnitureComment;

@Data
@Entity
@Table(name = "table_chair_comments")
public class TableChairArticleComment extends FurnitureComment<TableChairArticle, TableChairArticleCommentReply> {
}
