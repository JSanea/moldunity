package web.app.moldunity.entity.appliances.microwave;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.appliances.AppliancesCommentReply;

@Data
@Entity
@Table(name = "microwave_oven_comment_replies")
public class MicrowaveOvenCommentReply extends AppliancesCommentReply<MicrowaveOvenComment> {
}
