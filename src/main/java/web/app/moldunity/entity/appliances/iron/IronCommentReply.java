package web.app.moldunity.entity.appliances.iron;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.appliances.AppliancesCommentReply;

@Data
@Entity
@Table(name = "iron_comment_replies")
public class IronCommentReply extends AppliancesCommentReply<IronComment> {
}
