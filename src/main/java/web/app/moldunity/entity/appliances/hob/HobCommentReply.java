package web.app.moldunity.entity.appliances.hob;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.appliances.AppliancesCommentReply;

@Data
@Entity
@Table(name = "hob_comment_replies")
public class HobCommentReply extends AppliancesCommentReply<HobComment> {
}
