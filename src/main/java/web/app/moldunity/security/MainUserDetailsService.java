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
import web.app.moldunity.service.user.UserService;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class MainUserDetailsService implements UserDetailsService {
    private final String ROLE_PREFIX = "ROLE_";
    private final AsyncUserService asyncUserService;

    @Autowired
    public MainUserDetailsService(AsyncUserService asyncUserService) {
        this.asyncUserService = asyncUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String[] passwordAndRole;
        try {
            if (!asyncUserService.asyncExistUsername(username).get())
                throw new UsernameNotFoundException("%s not found".formatted(username));
            passwordAndRole = asyncUserService.asyncGetPasswordAndRoleByUsername(username).get();
        } catch (InterruptedException | ExecutionException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }

        String password = passwordAndRole[0];
        String role = passwordAndRole[1];

        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + role));

        return new org.springframework.security.core.userdetails.User(username, password, authorities);
    }
}
