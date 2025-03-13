package web.app.moldunity.entity.furniture.sofa_armchair;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.furniture.FurnitureView;

@Data
@Entity
@Table(name = "sofa_armchair_views")
public class SofaArmchairArticleView extends FurnitureView<SofaArmchairArticle> {
}
