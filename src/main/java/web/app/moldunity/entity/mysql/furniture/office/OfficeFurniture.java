package web.app.moldunity.entity.mysql.furniture.office;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.mysql.furniture.FurnitureArticle;

@Data
@Entity
@Table(name = "office")
public class OfficeFurniture extends FurnitureArticle {
}
