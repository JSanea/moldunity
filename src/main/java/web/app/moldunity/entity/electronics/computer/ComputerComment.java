package web.app.moldunity.entity.electronics.computer;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.electronics.ElectronicsComment;

@Data
@Entity
@Table(name = "computer_comments")
public class ComputerComment extends ElectronicsComment<Computer, ComputerCommentReply> {
}
