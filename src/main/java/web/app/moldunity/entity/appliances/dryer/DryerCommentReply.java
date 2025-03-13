package web.app.moldunity.entity.appliances.dryer;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.appliances.AppliancesCommentReply;

@Data
@Entity
@Table(name = "dryer_comment_replies")
public class DryerCommentReply extends AppliancesCommentReply<DryerComment> {
}
