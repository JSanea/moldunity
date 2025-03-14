package web.app.moldunity.entity.electronics.smartphone;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.electronics.ElectronicsCommentReply;

@Data
@Entity
@Table(name = "smartphone_comment_replies")
public class SmartphoneCommentReply extends ElectronicsCommentReply<SmartphoneComment> {
}
