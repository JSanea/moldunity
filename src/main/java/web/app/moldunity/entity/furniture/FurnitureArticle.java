package web.app.moldunity.entity.furniture;

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
public abstract class FurnitureArticle extends Article {
    @NotEmpty
    private String state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "furniture_id")
    @JsonIgnore
    private Furniture furniture;
}
