package web.app.moldunity.entity.electronics.laptop_tablet;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import web.app.moldunity.entity.AdvertComment;

@Getter
@Setter
@Entity
@Table(name = "laptop_table_comments")
public class LaptopTabletAdvertComment extends AdvertComment {
}
