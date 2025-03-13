package web.app.moldunity.entity.electronics.monitor;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.electronics.ElectronicsComment;
import web.app.moldunity.entity.electronics.ElectronicsCommentReply;

@Data
@Entity
@Table(name = "monitor_comment_replies")
public class MonitorCommentReply extends ElectronicsCommentReply<MonitorComment> {
}
