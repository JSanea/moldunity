package web.app.moldunity.entity.appliances.dishwasher;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.appliances.AppliancesCommentReply;

@Data
@Entity
@Table(name = "dishwasher_comment_replies")
public class DishWasherCommentReply extends AppliancesCommentReply<DishWasherComment> {
}
