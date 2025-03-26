package web.app.moldunity.entity.furniture;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
@EqualsAndHashCode(callSuper = true)
public class Furniture extends Category {
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "furniture", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<KitchenFurniture> kitchenFurnitures;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "furniture", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<BathroomFurniture> bathroomFurnitures;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "furniture", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<DiningFurniture> diningFurnitures;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "furniture", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<SofaArmchair> sofaArmchairs;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "furniture", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<TableChair> tableChairs;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "furniture", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Wardrobe> wardrobes;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "furniture", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<OfficeFurniture> officeFurnitures;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "furniture", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<OtherFurniture> otherFurnitures;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "furniture", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<FurnitureImage> furnitureImages;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "favorite", fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonIgnore
    private List<FavoriteFurniture> favoriteFurnitures;
}
