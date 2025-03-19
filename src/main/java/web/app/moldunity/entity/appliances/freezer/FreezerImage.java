package web.app.moldunity.entity.appliances.freezer;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.appliances.AppliancesImage;

@Data
@Entity
@Table(name = "freezer_images")
@EqualsAndHashCode(callSuper = true)
public class FreezerImage extends AppliancesImage<Freezer> {
}
