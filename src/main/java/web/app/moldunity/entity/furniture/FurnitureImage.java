package web.app.moldunity.entity.furniture;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.Image;

@Data
@Entity
@Table(name = "furniture_images")
@EqualsAndHashCode(callSuper = true)
public class FurnitureImage extends Image {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "furniture_id")
    @JsonIgnore
    private Furniture furniture;
}






