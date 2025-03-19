package web.app.moldunity.entity.electronics.computer;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.electronics.ElectronicsArticle;

@Data
@Entity
@Table(name = "computer")
@EqualsAndHashCode(callSuper = true)
public class Computer extends ElectronicsArticle<ComputerImage, ComputerView> {
    @NotEmpty
    private String processor;

    @NotEmpty
    private String memory;

    @NotEmpty
    private String ram;

    @NotEmpty
    private String os;
}
