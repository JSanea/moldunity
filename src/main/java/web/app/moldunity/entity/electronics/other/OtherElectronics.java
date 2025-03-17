package web.app.moldunity.entity.electronics.other;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.electronics.ElectronicsArticle;

@Data
@Entity
@Table(name = "other_electronics")
public class OtherElectronics extends ElectronicsArticle<OtherElectronicsImage, OtherElectronicsView> {
}
