package web.app.moldunity.service.entity.furniture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.app.moldunity.entity.furniture.bathroom.BathroomFurniture;
import web.app.moldunity.entity.user.User;
import web.app.moldunity.repository.furniture.BathroomRepository;
import web.app.moldunity.service.BaseJpaService;
import web.app.moldunity.service.user.UserService;
import web.app.moldunity.util.DateTimeUtil;
import web.app.moldunity.util.SecurityUtil;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BathroomArticleService extends BaseJpaService<BathroomFurniture, Long> {
    private final BathroomRepository bathroomRepository;
    private final UserService userService;

    @Autowired
    public BathroomArticleService(BathroomRepository bathroomRepository, UserService userService) {
        super(bathroomRepository);
        this.bathroomRepository = bathroomRepository;
        this.userService = userService;
    }

    public Long add(BathroomFurniture bathroomFurniture) throws UsernameNotFoundException {
        String username = SecurityUtil.getUsername();
        User user = userService.getByName(username);

        if(null == user || !username.equals(user.getUsername())){
            throw new UsernameNotFoundException("%s not found".formatted(username));
        }

        LocalDateTime localDateTime = DateTimeUtil.getDateTimeNow();
        bathroomFurniture.setCreatedAt(localDateTime);
        bathroomFurniture.setUpdateAt(localDateTime);
        bathroomFurniture.setRepublishedAt(localDateTime);
        bathroomFurniture.setUser(user);

        return super.save(bathroomFurniture).getId();
    }

    @Transactional(readOnly = true)
    public Optional<BathroomFurniture> getById(Long id){
        return bathroomRepository.findById(id);
    }
}










