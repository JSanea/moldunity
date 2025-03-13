package web.app.moldunity.entity.furniture.office;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.furniture.FurnitureImage;

@Data
@Entity
@Table(name = "office_images")
public class OfficeArticleImage extends FurnitureImage<OfficeArticle> {
}
