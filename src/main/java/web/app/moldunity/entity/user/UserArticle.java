package web.app.moldunity.entity.user;

import jakarta.persistence.FetchType;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import lombok.Data;
import web.app.moldunity.entity.appliances.freezer.Freezer;
import web.app.moldunity.entity.appliances.washingmachine.WashingMachine;
import web.app.moldunity.entity.furniture.bathroom.BathroomArticle;
import web.app.moldunity.entity.furniture.bedroom.BedroomArticle;
import web.app.moldunity.entity.furniture.dining.DiningArticle;
import web.app.moldunity.entity.furniture.kitchen.KitchenArticle;
import web.app.moldunity.entity.furniture.office.OfficeArticle;
import web.app.moldunity.entity.furniture.other.OtherFurnitureArticle;
import web.app.moldunity.entity.furniture.sofa_armchair.SofaArmchairArticle;
import web.app.moldunity.entity.furniture.table_chair.TableChairArticle;
import web.app.moldunity.entity.furniture.wardrobe.Wardrobe;

import java.util.List;

@Data
@MappedSuperclass
public abstract class UserArticle {
    /************************** Furniture **************************/
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<KitchenArticle> kitchenArticles;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<BathroomArticle> bathroomArticles;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<BedroomArticle> bedroomArticles;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<DiningArticle> diningArticles;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<SofaArmchairArticle> sofaArmchairArticles;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<TableChairArticle> tableChairArticles;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Wardrobe> wardrobes;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<OfficeArticle> officeArticles;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<OtherFurnitureArticle> otherFurnitureArticles;

    /************************** Appliances **************************/
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Freezer> freezers;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<WashingMachine> washingMachines;

}
