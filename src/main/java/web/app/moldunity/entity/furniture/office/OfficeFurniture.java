package web.app.moldunity.entity.furniture.office;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.furniture.FurnitureArticle;

@Data
@Entity
@Table(name = "office")
public class OfficeFurniture extends FurnitureArticle<OfficeFurnitureImage, OfficeFurnitureView> {
}
