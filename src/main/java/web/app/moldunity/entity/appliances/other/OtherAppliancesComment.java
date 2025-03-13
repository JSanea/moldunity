package web.app.moldunity.entity.appliances.other;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.appliances.AppliancesComment;

@Data
@Entity
@Table(name = "other_appliances_comments")
public class OtherAppliancesComment extends AppliancesComment<OtherAppliances, OtherAppliancesCommentReply> {
}
