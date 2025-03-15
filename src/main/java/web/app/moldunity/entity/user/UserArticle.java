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
import web.app.moldunity.entity.furniture.bathroom.BathroomFurniture;
import web.app.moldunity.entity.furniture.bedroom.BedroomFurniture;
import web.app.moldunity.entity.furniture.dining.DiningFurniture;
import web.app.moldunity.entity.furniture.kitchen.KitchenFurniture;
import web.app.moldunity.entity.furniture.office.OfficeFurniture;
import web.app.moldunity.entity.furniture.other.OtherFurniture;
import web.app.moldunity.entity.furniture.sofa_armchair.SofaArmchair;
import web.app.moldunity.entity.furniture.table_chair.TableChair;
import web.app.moldunity.entity.furniture.wardrobe.Wardrobe;

import java.util.Set;

@Data
@MappedSuperclass
public abstract class UserArticle {
    /************************** Furniture **************************/
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<KitchenFurniture> kitchenFurnitures;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<BathroomFurniture> bathroomFurnitures;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<BedroomFurniture> bedroomFurnitures;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<DiningFurniture> diningFurnitures;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<SofaArmchair> sofaArmchairs;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<TableChair> tableChairs;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Wardrobe> wardrobes;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<OfficeFurniture> officeFurnitures;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<OtherFurniture> otherFurnitures;

    /************************** Appliances **************************/
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Freezer> freezers;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<WashingMachine> washingMachines;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Dryer> dryers;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<DishWasher> dishWashers;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Cooker> cookers;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Hob> hobs;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Oven> ovens;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<MicrowaveOven> microwaveOvens;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<VacuumCleaner> vacuumCleaners;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<KitchenAppliances> kitchenAppliances;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Iron> irons;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Climate> climates;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<OtherAppliances> otherAppliances;

    /************************** Electronics **************************/
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Televisor> televisors;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Monitor> monitors;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<LaptopTablet> laptopTablets;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Smartphone> smartphones;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Computer> computers;


    /************************** Transport **************************/

    /************************** Immobile **************************/
}
























