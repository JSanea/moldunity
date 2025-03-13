package web.app.moldunity.entity.appliances.hob;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import web.app.moldunity.entity.appliances.AppliancesArticle;

@Data
@Entity
@Table(name = "hob")
public class Hob extends AppliancesArticle<HobComment, HobImage, HobView> {
    @NotEmpty
    private String brand;

    @NotEmpty
    private String type;

    @NotEmpty
    private String numOfBurners;
}
