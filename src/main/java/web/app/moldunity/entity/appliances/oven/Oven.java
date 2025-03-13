package web.app.moldunity.entity.appliances.oven;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import web.app.moldunity.entity.appliances.AppliancesArticle;

@Data
@Entity
@Table(name = "oven")
public class Oven extends AppliancesArticle<OvenComment, OvenImage, OvenView> {
    @NotEmpty
    private String brand;

    @NotEmpty
    private String type;
}
