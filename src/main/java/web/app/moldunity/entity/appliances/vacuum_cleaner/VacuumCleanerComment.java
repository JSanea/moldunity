package web.app.moldunity.entity.appliances.vacuum_cleaner;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.appliances.AppliancesComment;

@Data
@Entity
@Table(name = "vacuum_cleaner_comments")
public class VacuumCleanerComment extends AppliancesComment<VacuumCleaner, VacuumCleanerCommentReply> {
}
