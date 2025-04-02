package web.app.moldunity.security;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;


public class SecurityContextHelper {
    public static String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    }

    public static String getRole(){
        var a = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        return a.stream().map(GrantedAuthority::getAuthority).toList().get(0);
    }
}
