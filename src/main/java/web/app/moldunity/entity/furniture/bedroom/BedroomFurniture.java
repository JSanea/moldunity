package web.app.moldunity.entity.furniture.bedroom;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.furniture.FurnitureArticle;

@Data
@Entity
@Table(name = "bedroom")
public class BedroomFurniture extends FurnitureArticle {

}
