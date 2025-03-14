package web.app.moldunity.entity.electronics.televisor;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import web.app.moldunity.entity.electronics.ElectronicsArticle;

@Data
@Entity
@Table(name = "televisor")
public class Televisor extends ElectronicsArticle<TelevisorImage, TelevisorView> {
    @NotEmpty
    private String brand;

    @NotEmpty
    private Integer diagonal;

    @NotEmpty
    private String resolution;
}
