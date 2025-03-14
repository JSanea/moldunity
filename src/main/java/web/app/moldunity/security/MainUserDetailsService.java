package web.app.moldunity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import web.app.moldunity.entity.user.User;
import web.app.moldunity.service.user.UserService;

import java.util.ArrayList;

@Service
public class MainUserDetailsService implements UserDetailsService {
    private final String ROLE_PREFIX = "ROLE_";
    private final UserService userService;

    @Autowired
    public MainUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.findByName(username);

        if (null == user.getUsername()){
            throw new UsernameNotFoundException("%s not found".formatted(username));
        }

        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + user.getRole()));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
