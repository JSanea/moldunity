package web.app.moldunity.entity.appliances.freezer;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.appliances.AppliancesCommentReply;
import web.app.moldunity.entity.furniture.FurnitureCommentReply;

@Data
@Entity
@Table(name = "freezer_comment_replies")
public class FreezerCommentReply extends AppliancesCommentReply<FreezerComment> {
}
