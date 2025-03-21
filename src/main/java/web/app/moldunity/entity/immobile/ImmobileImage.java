package web.app.moldunity.entity.immobile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.Image;
import web.app.moldunity.entity.article.ArticleImage;
import web.app.moldunity.entity.furniture.Furniture;

@Data
@Entity
@Table(name = "immobile_images")
public abstract class ImmobileImage extends Image {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "immobile_id")
    @JsonIgnore
    private Immobile immobile;
}
