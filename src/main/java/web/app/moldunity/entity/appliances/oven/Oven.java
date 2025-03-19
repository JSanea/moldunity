package web.app.moldunity.entity.appliances.oven;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.appliances.AppliancesArticle;

@Data
@Entity
@Table(name = "oven")
@EqualsAndHashCode(callSuper = true)
public class Oven extends AppliancesArticle<OvenImage, OvenView> {
    @NotEmpty
    private String brand;

    @NotEmpty
    private String type;
}
