package web.app.moldunity.entity.mysql.furniture.dining;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.mysql.furniture.FurnitureArticle;

@Data
@Entity
@Table(name = "dining")
public class DiningFurniture extends FurnitureArticle {
}
