package web.app.moldunity.service.entity.furniture;

import org.springframework.stereotype.Service;
import web.app.moldunity.entity.furniture.kitchen.KitchenFurniture;
import web.app.moldunity.repository.furniture.KitchenRepository;
import web.app.moldunity.service.BaseJpaService;


import java.time.LocalDateTime;

@Service
public class KitchenArticleService extends BaseJpaService<KitchenFurniture, Long> {
    private final KitchenRepository kitchenRepository;

    public KitchenArticleService(KitchenRepository kitchenRepository) {
        super(kitchenRepository);
        this.kitchenRepository = kitchenRepository;
    }


    public KitchenFurniture add(KitchenFurniture kitchenArticle){
        kitchenArticle.setCategory("Mobila");
        kitchenArticle.setSubcategory("Bucatarie");
        kitchenArticle.setCreatedAt(LocalDateTime.now());
        kitchenArticle.setUpdateAt(LocalDateTime.now());
        //kitchenArticle.setUser();

        return super.save(kitchenArticle);
    }
}
