package web.app.moldunity.entity.appliances.cooker;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.appliances.AppliancesComment;

@Data
@Entity
@Table(name = "cooker_comments")
public class CookerComment extends AppliancesComment<Cooker, CookerCommentReply> {
}
