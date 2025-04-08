package web.app.moldunity.entity.mysql.furniture;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.mysql.Article;

@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
public abstract class FurnitureArticle extends Article {
    @NotEmpty
    private String state;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "furniture_id")
    @JsonIgnore
    private Furniture furniture;
}
