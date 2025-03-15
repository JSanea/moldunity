package web.app.moldunity.entity.furniture.bathroom;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.furniture.FurnitureView;

@Data
@Entity
@Table(name = "bathroom_views")
public class BathroomFurnitureView extends FurnitureView<BathroomFurniture> {
}
