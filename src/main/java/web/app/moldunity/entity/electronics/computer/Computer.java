package web.app.moldunity.entity.electronics.computer;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import web.app.moldunity.entity.electronics.ElectronicsArticle;

@Data
@Entity
@Table(name = "computer")
public class Computer extends ElectronicsArticle<ComputerComment, ComputerImage, ComputerView> {
    @NotEmpty
    private String brand;

    @NotEmpty
    private String processor;

    @NotEmpty
    private String memory;

    @NotEmpty
    private String ram;

    @NotEmpty
    private String os;
}
