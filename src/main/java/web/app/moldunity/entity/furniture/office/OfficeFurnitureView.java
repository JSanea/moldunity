package web.app.moldunity.entity.furniture.office;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.furniture.FurnitureView;

@Data
@Entity
@Table(name = "office_views")
public class OfficeFurnitureView extends FurnitureView<OfficeFurniture> {
}
