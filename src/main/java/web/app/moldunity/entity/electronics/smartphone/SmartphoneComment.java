package web.app.moldunity.entity.electronics.smartphone;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.electronics.ElectronicsComment;

@Data
@Entity
@Table(name = "smartphone_comments")
public class SmartphoneComment extends ElectronicsComment<Smartphone, SmartphoneCommentReply> {
}
