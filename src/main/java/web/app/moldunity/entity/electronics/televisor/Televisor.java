package web.app.moldunity.entity.electronics.televisor;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.electronics.ElectronicsArticle;

@Data
@Entity
@Table(name = "televisor")
@EqualsAndHashCode(callSuper = true)
public class Televisor extends ElectronicsArticle<TelevisorImage, TelevisorView> {
    @NotEmpty
    private Integer diagonal;

    @NotEmpty
    private String resolution;
}
