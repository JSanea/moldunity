package web.app.moldunity.entity.furniture.bathroom;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.furniture.FurnitureView;

@Data
@Entity
@Table(name = "bathroom_views")
@EqualsAndHashCode(callSuper = true)
public class BathroomFurnitureView extends FurnitureView<BathroomFurniture> {
}
