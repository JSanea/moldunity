package web.app.moldunity.entity.furniture.wardrobe;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import web.app.moldunity.entity.furniture.FurnitureArticle;

@Data
@Entity
@Table(name = "wardrobe")
public class Wardrobe extends FurnitureArticle<WardrobeComment, WardrobeImage, WardrobeView> {
    @NotEmpty
    private String height;

    @NotEmpty
    private String width;

    @NotEmpty
    private String color;
}
