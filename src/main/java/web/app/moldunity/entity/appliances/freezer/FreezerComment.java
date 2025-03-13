package web.app.moldunity.entity.appliances.freezer;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.appliances.AppliancesComment;
import web.app.moldunity.entity.furniture.FurnitureComment;

@Data
@Entity
@Table(name = "freezer_comments")
public class FreezerComment extends AppliancesComment<Freezer, FreezerCommentReply> {
}
