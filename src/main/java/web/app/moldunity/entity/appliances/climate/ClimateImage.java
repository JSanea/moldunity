package web.app.moldunity.entity.appliances.climate;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.appliances.AppliancesImage;

@Data
@Entity
@Table(name = "climate_images")
public class ClimateImage extends AppliancesImage<Climate> {
}
