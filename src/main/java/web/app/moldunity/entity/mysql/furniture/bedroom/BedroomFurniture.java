package web.app.moldunity.entity.mysql.furniture.bedroom;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.mysql.furniture.FurnitureArticle;

@Data
@Entity
@Table(name = "bedroom")
public class BedroomFurniture extends FurnitureArticle {

}
