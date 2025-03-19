package web.app.moldunity.util;

public class WhiteUrls {
    public static String[] getUrls(){
        return new String[]{
                "/user/**",
                "/furniture/bathroom/**",
                "/furniture/bedroom/**",
                "/furniture/kitchen/**",
                "/furniture/dining/**",
                "/furniture/office/**",
        };
    }
}
