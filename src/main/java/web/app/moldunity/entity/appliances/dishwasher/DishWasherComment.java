package web.app.moldunity.entity.appliances.dishwasher;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.appliances.AppliancesComment;

@Data
@Entity
@Table(name = "dishwasher_comments")
public class DishWasherComment extends AppliancesComment<DishWasher, DishWasherCommentReply> {
}
