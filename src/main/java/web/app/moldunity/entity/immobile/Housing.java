package web.app.moldunity.entity.immobile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.article.Article;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "immobile_id")
    @JsonIgnore
    private Immobile immobile;
}
