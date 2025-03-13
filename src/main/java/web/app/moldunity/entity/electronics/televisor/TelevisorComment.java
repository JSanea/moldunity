package web.app.moldunity.entity.electronics.televisor;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.appliances.AppliancesComment;

@Data
@Entity
@Table(name = "televisor_comment")
public class TelevisorComment extends AppliancesComment<Televisor, TelevisorCommentReply> {
}
