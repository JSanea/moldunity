package web.app.moldunity.entity.appliances.vacuum_cleaner;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.appliances.AppliancesCommentReply;

@Data
@Entity
@Table(name = "vacuum_cleaner_comment_replies")
public class VacuumCleanerCommentReply extends AppliancesCommentReply<VacuumCleanerComment> {
}
