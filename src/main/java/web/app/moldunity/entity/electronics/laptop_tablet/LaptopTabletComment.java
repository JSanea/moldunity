package web.app.moldunity.entity.electronics.laptop_tablet;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.electronics.ElectronicsComment;

@Data
@Entity
@Table(name = "laptop_tablet_comments")
public class LaptopTabletComment extends ElectronicsComment<LaptopTablet, LaptopTabletCommentReply> {
}
