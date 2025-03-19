package web.app.moldunity.entity.furniture.office;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.furniture.FurnitureView;

@Data
@Entity
@Table(name = "office_views")
@EqualsAndHashCode(callSuper = true)
public class OfficeFurnitureView extends FurnitureView<OfficeFurniture> {
}
