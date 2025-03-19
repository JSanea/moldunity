package web.app.moldunity.entity.appliances.climate;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.appliances.AppliancesImage;

@Data
@Entity
@Table(name = "climate_images")
@EqualsAndHashCode(callSuper = true)
public class ClimateImage extends AppliancesImage<Climate> {
}
