package web.app.moldunity.entity.electronics.computer;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.electronics.ElectronicsCommentReply;

@Data
@Entity
@Table(name = "computer_comment_replies")
public class ComputerCommentReply extends ElectronicsCommentReply<ComputerComment> {
}
