package web.app.moldunity.entity.appliances.washingmachine;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.appliances.AppliancesComment;

@Data
@Entity
@Table(name = "washing_machine_comments")
public class WashingMachineComment extends AppliancesComment<WashingMachine, WashingMachineCommentReply> {
}
