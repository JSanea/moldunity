package web.app.moldunity.entity.furniture.office;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.furniture.FurnitureImage;

@Data
@Entity
@Table(name = "office_images")
@EqualsAndHashCode(callSuper = true)
public class OfficeFurnitureImage extends FurnitureImage<OfficeFurniture> {
}
