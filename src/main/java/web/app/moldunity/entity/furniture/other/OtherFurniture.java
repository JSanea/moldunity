package web.app.moldunity.entity.furniture.other;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.furniture.FurnitureArticle;

@Data
@Entity
@Table(name = "other_furniture")
@EqualsAndHashCode(callSuper = true)
public class OtherFurniture extends FurnitureArticle<OtherFurnitureImage, OtherFurnitureView> {
}
