package web.app.moldunity.entity.immobile.home;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.immobile.Housing;

@Data
@Entity
@Table(name = "home")
@EqualsAndHashCode(callSuper = true)
public class Home extends Housing {
    @NotEmpty
    private String floors;

    @NotEmpty
    private String landArea;
}
