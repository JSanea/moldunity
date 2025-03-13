package web.app.moldunity.entity.appliances.climate;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.appliances.AppliancesCommentReply;

@Data
@Entity
@Table(name = "climate_comment_replies")
public class ClimateCommentReply extends AppliancesCommentReply<ClimateComment> {
}
