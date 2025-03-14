package web.app.moldunity.entity.user;

import jakarta.persistence.FetchType;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import lombok.Data;
import web.app.moldunity.entity.appliances.climate.Climate;
import web.app.moldunity.entity.appliances.cooker.Cooker;
import web.app.moldunity.entity.appliances.dishwasher.DishWasher;
import web.app.moldunity.entity.appliances.dryer.Dryer;
import web.app.moldunity.entity.appliances.freezer.Freezer;
import web.app.moldunity.entity.appliances.hob.Hob;
import web.app.moldunity.entity.appliances.iron.Iron;
import web.app.moldunity.entity.appliances.kitchen.KitchenAppliances;
import web.app.moldunity.entity.appliances.microwave.MicrowaveOven;
import web.app.moldunity.entity.appliances.other.OtherAppliances;
import web.app.moldunity.entity.appliances.oven.Oven;
import web.app.moldunity.entity.appliances.vacuum_cleaner.VacuumCleaner;
import web.app.moldunity.entity.electronics.computer.Computer;
import web.app.moldunity.entity.electronics.laptop_tablet.LaptopTablet;
import web.app.moldunity.entity.electronics.monitor.Monitor;
import web.app.moldunity.entity.electronics.smartphone.Smartphone;
import web.app.moldunity.entity.electronics.televisor.Televisor;
import web.app.moldunity.entity.appliances.washing_machine.WashingMachine;
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

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Dryer> dryers;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<DishWasher> dishWashers;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Cooker> cookers;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Hob> hobs;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Oven> ovens;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<MicrowaveOven> microwaveOvens;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<VacuumCleaner> vacuumCleaners;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<KitchenAppliances> kitchenAppliances;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Iron> irons;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Climate> climates;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<OtherAppliances> otherAppliances;

    /************************** Electronics **************************/
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Televisor> televisors;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Monitor> monitors;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<LaptopTablet> laptopTablets;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Smartphone> smartphones;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Computer> computers;


    /************************** Transport **************************/

    /************************** Immobile **************************/
}
























