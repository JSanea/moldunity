package web.app.moldunity.entity.appliances.iron;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.appliances.AppliancesArticle;

@Data
@Entity
@Table(name = "iron")
@EqualsAndHashCode(callSuper = true)
public class Iron extends AppliancesArticle<IronImage, IronView> {
    @NotEmpty
    private String brand;

    @NotEmpty
    private String power;
}
