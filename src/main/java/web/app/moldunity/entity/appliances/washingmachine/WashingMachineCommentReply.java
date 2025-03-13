package web.app.moldunity.entity.appliances.washingmachine;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.appliances.AppliancesCommentReply;

@Data
@Entity
@Table(name = "washing_machine_comment_replies")
public class WashingMachineCommentReply extends AppliancesCommentReply<WashingMachineComment> {
}
