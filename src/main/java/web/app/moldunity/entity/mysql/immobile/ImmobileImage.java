package web.app.moldunity.entity.mysql.immobile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.mysql.Image;

@Data
@Entity
@Table(name = "immobile_images")
@EqualsAndHashCode(callSuper = true)
public class ImmobileImage extends Image {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "immobile_id")
    @JsonIgnore
    private Immobile immobile;
}
