package web.app.moldunity.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.app.moldunity.entity.user.User;
import web.app.moldunity.repository.UserRepository;
import web.app.moldunity.service.BaseJpaService;

import java.time.LocalDate;

@Service
public class UserService extends BaseJpaService<User, Long> {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        super(userRepository);
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User addUser(User user){
        user.setRole("USER");
        user.setCreatedAt(LocalDate.now());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setBlocked(false);
        return super.save(user);
    }

    @Transactional(readOnly = true)
    public User getByName(String username){
        User user = userRepository.getByName(username).orElseGet(User::new);

        if("ADMIN".equals(user.getRole()))
            return new User();

        user.setPassword("********");
        return user;
    }

    @Transactional(readOnly = true)
    public String getPasswordByUsername(String username){
        return userRepository.getPasswordByUsername(username);
    }

    @Transactional(readOnly = true)
    public String getRoleByUsername(String username){
        return userRepository.getRoleByUsername(username);
    }

    @Transactional(readOnly = true)
    public String[] getPasswordAndRoleByUsername(String username){
        return userRepository.getPasswordAndRoleByUsername(username).get(0).split(",");
    }

    @Transactional(readOnly = true)
    public Boolean existUsername(String username){
        return username.equals(userRepository.existUsername(username));
    }

    @Transactional(readOnly = true)
    public Boolean existEmail(String email){
        return email.equals(userRepository.existEmail(email));
    }
}
