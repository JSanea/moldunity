package web.app.moldunity.entity.mysql.immobile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.mysql.Article;

@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
public abstract class Housing extends Article {
    @NotEmpty
    private String area;

    @NotEmpty
    private String kitchenArea;

    @NotEmpty
    private String rooms;

    @OneToOne
    @JoinColumn(name = "immobile_id")
    @JsonIgnore
    private Immobile immobile;
}
