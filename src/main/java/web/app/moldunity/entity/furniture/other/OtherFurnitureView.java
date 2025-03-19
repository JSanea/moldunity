package web.app.moldunity.entity.furniture.other;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.furniture.FurnitureView;

@Data
@Entity
@Table(name = "other_furniture_views")
@EqualsAndHashCode(callSuper = true)
public class OtherFurnitureView extends FurnitureView<OtherFurniture> {
}
