package web.app.moldunity.service.entity.furniture;

import org.springframework.stereotype.Service;
import web.app.moldunity.entity.furniture.kitchen.KitchenArticle;
import web.app.moldunity.repository.furniture.KitchenRepository;
import web.app.moldunity.service.BaseJpaService;

@Service
public class KitchenService extends BaseJpaService<KitchenArticle, Long> {
    private final KitchenRepository kitchenRepository;

    public KitchenService(KitchenRepository kitchenRepository) {
        super(kitchenRepository);
        this.kitchenRepository = kitchenRepository;
    }
}
