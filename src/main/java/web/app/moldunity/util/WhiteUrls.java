package web.app.moldunity.util;

public class WhiteUrls {
    public static String[] getUrls(){
        return new String[]{
                "/user/**",
                "/register",
                "/authenticate",
                //***** Immobile *****//

                //***** Transport *****//
                "/transport/car/**",

                //***** Furniture *****//
                "/furniture/**",
                "/furniture/bathroom/**",
                "/furniture/kitchen/**",

                //***** Appliances *****//
        };
    }
}
