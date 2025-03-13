package web.app.moldunity.entity.appliances.hob;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.appliances.AppliancesComment;

@Data
@Entity
@Table(name = "hob_comments")
public class HobComment extends AppliancesComment<Hob, HobCommentReply> {
}
