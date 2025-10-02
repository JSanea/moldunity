package web.app.moldunity.enums;

import web.app.moldunity.entity.postgres.ad.Subcategory;
import web.app.moldunity.entity.postgres.real_estate.Apartment;
import web.app.moldunity.entity.postgres.real_estate.Home;
import web.app.moldunity.entity.postgres.transport.BusMinibus;
import web.app.moldunity.entity.postgres.transport.Car;

import java.util.Arrays;
import java.util.Optional;

public enum AdSubtype {
    // ******************* Transport *******************
    CAR(100, "Car", Car.class),
    BUS_MINIBUS(101, "Bus-Minibus", BusMinibus.class),

    // ******************* Real Estate *******************
    APARTMENT(200, "Apartment", Apartment.class),
    HOUSE(    201, "House", Home.class);

    // ******************* Home Appliances *******************
//    HOME_APPLIANCES(300, "Home-Appliances", Object.class, subcategoryClassType),

    // ******************* Furniture *******************
//    FURNITURE(400, "Furniture", Object.class, subcategoryClassType),

    // ******************* Electronics *******************
//    ELECTRONICS(500, "Electronics", Object.class, subcategoryClassType),
//    SERVICE(600, "Service", Object.class, subcategoryClassType),
//    JOB(700, "Job", Object.class, subcategoryClassType),
//    OTHER(800, "Other", Object.class);

    private final Integer code;

    private final String subcategoryName;

    private final Class<? extends Subcategory> subcategoryClassType;

    AdSubtype(Integer code, String subcategoryName, Class<? extends Subcategory> subcategoryClassType) {
        this.code = code;
        this.subcategoryName = subcategoryName;
        this.subcategoryClassType = subcategoryClassType;
    }

    public String getSubcategoryName() {
        return this.subcategoryName;
    }

    public Integer getCode() {
        return this.code;
    }

    public Class<? extends Subcategory> getSubcategoryType(){
        return subcategoryClassType;
    }

    public static Optional<AdSubtype> fromSubcategoryName(String name) {
        return Arrays.stream(values())
                .filter(c -> c.subcategoryName.equalsIgnoreCase(name))
                .findFirst();
    }

    public static Optional<AdSubtype> fromSubcategory(Class<? extends Subcategory> subcategoryClass) {
        return Arrays.stream(values())
                .filter(c -> c.subcategoryClassType.equals(subcategoryClass))
                .findFirst();
    }

    public static boolean hasSubcategoryName(String name) {
        return Arrays.stream(values())
                .anyMatch(c -> c.subcategoryName.equalsIgnoreCase(name));
    }
}
