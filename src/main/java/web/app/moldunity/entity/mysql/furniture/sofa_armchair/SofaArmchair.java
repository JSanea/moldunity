package web.app.moldunity.entity.mysql.furniture.sofa_armchair;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.mysql.furniture.FurnitureArticle;

@Data
@Entity
@Table(name = "sofa_armchair")
public class SofaArmchair extends FurnitureArticle{
}
