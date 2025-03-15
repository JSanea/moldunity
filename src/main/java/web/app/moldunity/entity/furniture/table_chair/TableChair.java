package web.app.moldunity.entity.furniture.table_chair;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.furniture.FurnitureArticle;

@Data
@Entity
@Table(name = "table_chair")
public class TableChair extends FurnitureArticle<TableChairImage, TableChairView> {
}
