package web.app.moldunity.entity.immobile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.article.Article;
import web.app.moldunity.entity.furniture.Furniture;
import web.app.moldunity.entity.user.User;

import java.util.Set;

@Data
@MappedSuperclass
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
