package web.app.moldunity.util;

public class WhiteUrls {
    public static String[] getUrls(){
        return new String[]{
                "/user/**",
                "/register",
                //***** Immobile *****//

                //***** Transport *****//
                "/transport/car/**",

                //***** Furniture *****//
                "/furniture/bathroom/**",
                "/furniture/bedroom/**",
                "/furniture/kitchen/**",
                "/furniture/dining/**",
                "/furniture/office/**",

                //***** Appliances *****//
        };
    }
}
