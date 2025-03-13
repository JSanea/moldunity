package web.app.moldunity.entity.appliances.dryer;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.appliances.AppliancesComment;

@Data
@Entity
@Table(name = "dryer_comments")
public class DryerComment extends AppliancesComment<Dryer, DryerCommentReply> {
}
