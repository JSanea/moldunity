package web.app.moldunity.entity.furniture.dining;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.furniture.FurnitureView;

@Data
@Entity
@Table(name = "dining_views")
@EqualsAndHashCode(callSuper = true)
public class DiningFurnitureView extends FurnitureView<DiningFurniture> {
}
