package web.app.moldunity.entity.appliances.other;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.appliances.AppliancesCommentReply;

@Data
@Entity
@Table(name = "other_appliances_comment_replies")
public class OtherAppliancesCommentReply extends AppliancesCommentReply<OtherAppliancesComment> {
}
