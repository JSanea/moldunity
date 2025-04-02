package web.app.moldunity.entity.mysql.furniture.kitchen;

import jakarta.persistence.*;
import lombok.Data;
import web.app.moldunity.entity.mysql.furniture.FurnitureArticle;


@Data
@Entity
@Table(name = "kitchen_furniture")
public class KitchenFurniture extends FurnitureArticle {

}
