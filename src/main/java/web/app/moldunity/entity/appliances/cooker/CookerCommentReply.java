package web.app.moldunity.entity.appliances.cooker;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.appliances.AppliancesCommentReply;

@Data
@Entity
@Table(name = "cooker_comment_replies")
public class CookerCommentReply extends AppliancesCommentReply<CookerComment> {
}
