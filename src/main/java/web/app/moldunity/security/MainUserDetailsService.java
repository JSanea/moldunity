package web.app.moldunity.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import web.app.moldunity.service.async.AsyncUserService;

import java.util.ArrayList;

@Service
@Slf4j
public class MainUserDetailsService implements UserDetailsService {
    private final String ROLE_PREFIX = "ROLE_";
    private final AsyncUserService asyncUserService;
    private String password;
    private String role;

    @Autowired
    public MainUserDetailsService(AsyncUserService asyncUserService) {
        this.asyncUserService = asyncUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        asyncUserService.asyncExistUsername(username)
                .thenCompose(exist -> {
                    if(!exist)
                        throw new UsernameNotFoundException("%s not found".formatted(username));
                    return asyncUserService.asyncGetPasswordAndRoleByUsername(username);
                })
                        .thenAccept(passwordAndRole -> {
                            password = passwordAndRole[0];
                            role = passwordAndRole[1];
                            authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + role));
                        }).join();

        return new org.springframework.security.core.userdetails.User(username, password, authorities);
    }
}
