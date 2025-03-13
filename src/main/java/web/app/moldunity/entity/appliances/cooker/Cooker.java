package web.app.moldunity.entity.appliances.cooker;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import web.app.moldunity.entity.appliances.AppliancesArticle;

@Data
@Entity
@Table(name = "cooker")
public class Cooker extends AppliancesArticle<CookerComment, CookerImage, CookerView> {
    @NotEmpty
    private String brand;

    @NotEmpty
    private String hobType;

    @NotEmpty
    private String ovenType;

    @NotEmpty
    private String numOfBurners;
}















