package web.app.moldunity.entity.appliances.microwave;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.appliances.AppliancesComment;

@Data
@Entity
@Table(name = "microwave_oven_comments")
public class MicrowaveOvenComment extends AppliancesComment<MicrowaveOven, MicrowaveOvenCommentReply> {
}
