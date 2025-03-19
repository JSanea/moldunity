package web.app.moldunity.entity.furniture.sofa_armchair;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.furniture.FurnitureArticle;

@Data
@Entity
@Table(name = "sofa_armchair")
@EqualsAndHashCode(callSuper = true)
public class SofaArmchair extends FurnitureArticle<SofaArmchairImage, SofaArmchairView> {
}
