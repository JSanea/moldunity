package web.app.moldunity.entity.furniture.kitchen;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.furniture.FurnitureView;

@Data
@Entity
@Table(name = "kitchen_furniture_views")
@EqualsAndHashCode(callSuper = true)
public class KitchenFurnitureView extends FurnitureView<KitchenFurniture> {

}
