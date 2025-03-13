package web.app.moldunity.entity.electronics.televisor;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.appliances.AppliancesCommentReply;

@Data
@Entity
@Table(name = "televisor_comment_replies")
public class TelevisorCommentReply extends AppliancesCommentReply<TelevisorComment> {
}
