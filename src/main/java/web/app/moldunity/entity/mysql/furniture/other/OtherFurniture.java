package web.app.moldunity.entity.mysql.furniture.other;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.mysql.furniture.FurnitureArticle;

@Data
@Entity
@Table(name = "other_furniture")
public class OtherFurniture extends FurnitureArticle {
}
