package web.app.moldunity.entity.furniture.bathroom;

import jakarta.persistence.*;
import lombok.Data;
import web.app.moldunity.entity.furniture.FurnitureArticle;

@Data
@Entity
@Table(name = "bathroom")
public class BathroomFurniture extends FurnitureArticle<BathroomFurnitureImage, BathroomFurnitureView> {

}
