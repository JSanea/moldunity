package web.app.moldunity.entity.furniture.dining;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.furniture.FurnitureView;

@Data
@Entity
@Table(name = "dining_views")
public class DiningArticleView extends FurnitureView<DiningArticle> {
}
