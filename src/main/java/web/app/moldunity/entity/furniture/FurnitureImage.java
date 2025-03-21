package web.app.moldunity.entity.furniture;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.Image;

@Data
@Entity
@Table(name = "furniture_images")
public class FurnitureImage extends Image {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "furniture_id")
    @JsonIgnore
    private Furniture furniture;
}






