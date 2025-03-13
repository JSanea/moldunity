package web.app.moldunity.entity.appliances.kitchen;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.appliances.AppliancesCommentReply;

@Data
@Entity
@Table(name = "kitchen_appliances_comment_replies")
public class KitchenAppliancesCommentReply extends AppliancesCommentReply<KitchenAppliancesComment> {
}
