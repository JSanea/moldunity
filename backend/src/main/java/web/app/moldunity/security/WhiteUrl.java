package web.app.moldunity.security;

public final class WhiteUrl {
    private WhiteUrl(){}

    public static String[] urls(){
        return new String[]{
                "/api/login",
                "/api/register",
                "/api/ads/**",
                "/api/profile/**",
                "/api/auth/refresh",
                "/api/forgot-password",
                "/api/reset-password",
                "/api/is-authenticate",
                "/v3/api-docs",
                "/swagger-ui.html",
                "/swagger-ui/**",
                "/docs"
        };
    }
}
