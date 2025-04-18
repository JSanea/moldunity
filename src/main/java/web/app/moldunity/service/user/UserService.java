package web.app.moldunity.service.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import web.app.moldunity.entity.mysql.user.User;
import web.app.moldunity.enums.ChangePasswordStatus;
import web.app.moldunity.repository.mysql.UserRepository;
import web.app.moldunity.service.BaseJpaService;

import java.time.LocalDate;

@Service
@Slf4j
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
        var out = super.save(user);
        out.setIgnoredFields();
        return out;
    }

    public User getByName(String username){
        Long id = userRepository.getIdByName(username);

        if(null == id)
            return new User();

        User user = userRepository.findById(id).orElseGet(User::new);
        if("ADMIN".equals(user.getRole()))
            return new User();

        user.setIgnoredFields();

        return user;
    }

    public String getPasswordByUsername(String username){
        return userRepository.getPasswordByUsername(username);
    }

    public String getRoleByUsername(String username){
        return userRepository.getRoleByUsername(username);
    }

    public String[] getPasswordAndRoleByUsername(String username){
        return userRepository.getPasswordAndRoleByUsername(username).get(0).split(",");
    }

    public Boolean existUsername(String username){
        return username.equals(userRepository.existUsername(username));
    }

    public Boolean existEmail(String email){
        return email.equals(userRepository.existEmail(email));
    }

    public Long getIdByUsername(String username){
        return userRepository.getIdByName(username);
    }

    public void updatePassword(String email, String password){
        userRepository.updatePassword(email, password);
    }

    public ChangePasswordStatus changePassword(String username, String currentPassword, String newPassword){
        if(!passwordEncoder.matches(userRepository.getPasswordByUsername(username), currentPassword))
            return ChangePasswordStatus.INVALID_CURRENT_PASSWORD;
        try {
            userRepository.changePassword(username, newPassword);
            return ChangePasswordStatus.SUCCESS;
        } catch (Exception e) {
            log.error(e.getMessage());
            return ChangePasswordStatus.ERROR;
        }
    }
}
