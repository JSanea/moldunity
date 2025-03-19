package web.app.moldunity.entity.furniture.table_chair;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.furniture.FurnitureView;

@Data
@Entity
@Table(name = "table_chair_views")
@EqualsAndHashCode(callSuper = true)
public class TableChairView extends FurnitureView<TableChair> {
}
