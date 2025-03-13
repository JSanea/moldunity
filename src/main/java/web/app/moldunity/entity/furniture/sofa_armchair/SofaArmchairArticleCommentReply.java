package web.app.moldunity.entity.furniture.sofa_armchair;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.furniture.FurnitureCommentReply;

@Data
@Entity
@Table(name = "sofa_armchair_comment_replies")
public class SofaArmchairArticleCommentReply extends FurnitureCommentReply<SofaArmchairArticleComment> {
}
