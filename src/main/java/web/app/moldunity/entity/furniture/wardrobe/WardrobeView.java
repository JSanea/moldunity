package web.app.moldunity.entity.furniture.wardrobe;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.furniture.FurnitureImage;

@Data
@Entity
@Table(name = "wardrobe_views")
@EqualsAndHashCode(callSuper = true)
public class WardrobeView extends FurnitureImage<Wardrobe> {
}
