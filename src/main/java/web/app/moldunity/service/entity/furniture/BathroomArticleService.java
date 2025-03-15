package web.app.moldunity.service.entity.furniture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.app.moldunity.entity.furniture.bathroom.BathroomFurniture;
import web.app.moldunity.repository.furniture.BathroomRepository;
import web.app.moldunity.service.BaseJpaService;

@Service
public class BathroomArticleService extends BaseJpaService<BathroomFurniture, Long> {
    private final BathroomRepository bathroomRepository;

    @Autowired
    public BathroomArticleService(BathroomRepository bathroomRepository) {
        super(bathroomRepository);
        this.bathroomRepository = bathroomRepository;
    }
}
