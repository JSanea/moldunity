package web.app.moldunity.entity.appliances.iron;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.appliances.AppliancesComment;

@Data
@Entity
@Table(name = "iron_comments")
public class IronComment extends AppliancesComment<Iron, IronCommentReply> {
}
