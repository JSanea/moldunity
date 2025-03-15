package web.app.moldunity.entity.furniture.table_chair;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.furniture.FurnitureImage;

@Data
@Entity
@Table(name = "table_chair_images")
public class TableChairImage extends FurnitureImage<TableChair> {
}
