package web.app.moldunity.util;

public class WhiteUrls {
    public static String[] getUrls(){
        return new String[]{
                "/user/**",
                "/register",
                "/authenticate",
                "/verification-code",
                "/update-password",

                //***** Immobile *****//
                "/immobile/**",

                //***** Transport *****//

                //***** Furniture *****//
                "/furniture/**",

                //***** Appliances *****//
        };
    }
}
