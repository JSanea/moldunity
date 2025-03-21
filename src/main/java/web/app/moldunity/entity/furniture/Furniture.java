package web.app.moldunity.entity.furniture;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.Category;
import web.app.moldunity.entity.furniture.bathroom.BathroomFurniture;
import web.app.moldunity.entity.furniture.dining.DiningFurniture;
import web.app.moldunity.entity.furniture.kitchen.KitchenFurniture;
import web.app.moldunity.entity.furniture.office.OfficeFurniture;
import web.app.moldunity.entity.furniture.other.OtherFurniture;
import web.app.moldunity.entity.furniture.sofa_armchair.SofaArmchair;
import web.app.moldunity.entity.furniture.table_chair.TableChair;
import web.app.moldunity.entity.furniture.wardrobe.Wardrobe;

import java.util.List;


@Data
@Entity
@Table(name = "furniture")
public class Furniture extends Category {
    @OneToMany(mappedBy = "furniture", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<KitchenFurniture> kitchenFurnitures;

    @OneToMany(mappedBy = "furniture", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<BathroomFurniture> bathroomFurnitures;

    @OneToMany(mappedBy = "furniture", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<DiningFurniture> diningFurnitures;

    @OneToMany(mappedBy = "furniture", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<SofaArmchair> sofaArmchairs;

    @OneToMany(mappedBy = "furniture", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<TableChair> tableChairs;

    @OneToMany(mappedBy = "furniture", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Wardrobe> wardrobes;

    @OneToMany(mappedBy = "furniture", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<OfficeFurniture> officeFurnitures;

    @OneToMany(mappedBy = "furniture", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<OtherFurniture> otherFurnitures;

    @OneToMany(mappedBy = "furniture", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<FurnitureImage> furnitureImages;
}
