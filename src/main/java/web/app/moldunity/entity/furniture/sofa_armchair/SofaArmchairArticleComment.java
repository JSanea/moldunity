package web.app.moldunity.entity.furniture.sofa_armchair;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.furniture.FurnitureComment;

@Data
@Entity
@Table(name = "sofa_armchair_comments")
public class SofaArmchairArticleComment extends FurnitureComment<SofaArmchairArticle, SofaArmchairArticleCommentReply> {
}
