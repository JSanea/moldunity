package web.app.moldunity.entity.mysql.furniture;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.mysql.Category;
import web.app.moldunity.entity.mysql.furniture.bathroom.BathroomFurniture;
import web.app.moldunity.entity.mysql.furniture.dining.DiningFurniture;
import web.app.moldunity.entity.mysql.furniture.kitchen.KitchenFurniture;
import web.app.moldunity.entity.mysql.furniture.office.OfficeFurniture;
import web.app.moldunity.entity.mysql.furniture.other.OtherFurniture;
import web.app.moldunity.entity.mysql.furniture.sofa_armchair.SofaArmchair;
import web.app.moldunity.entity.mysql.furniture.table_chair.TableChair;
import web.app.moldunity.entity.mysql.furniture.wardrobe.Wardrobe;

import java.util.List;


@Data
@Entity
@Table(name = "furniture")
@EqualsAndHashCode(callSuper = true)
public class Furniture extends Category {
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "furniture", fetch = FetchType.EAGER, orphanRemoval = true)
    private KitchenFurniture kitchenFurniture;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "furniture", fetch = FetchType.EAGER, orphanRemoval = true)
    private BathroomFurniture bathroomFurniture;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "furniture", fetch = FetchType.EAGER, orphanRemoval = true)
    private DiningFurniture diningFurniture;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "furniture", fetch = FetchType.EAGER, orphanRemoval = true)
    private SofaArmchair sofaArmchair;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "furniture", fetch = FetchType.EAGER, orphanRemoval = true)
    private TableChair tableChair;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "furniture", fetch = FetchType.EAGER, orphanRemoval = true)
    private Wardrobe wardrobe;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "furniture", fetch = FetchType.EAGER, orphanRemoval = true)
    private OfficeFurniture officeFurniture;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "furniture", fetch = FetchType.EAGER, orphanRemoval = true)
    private OtherFurniture otherFurniture;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "furniture", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<FurnitureImage> furnitureImages;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "favorite", fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonIgnore
    private List<FavoriteFurniture> favoriteFurnitures;
}
