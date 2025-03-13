package web.app.moldunity.service.entity.furniture;

import org.springframework.stereotype.Service;
import web.app.moldunity.entity.furniture.kitchen.KitchenArticle;
import web.app.moldunity.entity.furniture.kitchen.KitchenArticleImage;
import web.app.moldunity.repository.furniture.KitchenRepository;
import web.app.moldunity.service.BaseJpaService;


import java.time.LocalDateTime;

@Service
public class KitchenArticleService extends BaseJpaService<KitchenArticle, Long> {
    private final KitchenRepository kitchenRepository;

    public KitchenArticleService(KitchenRepository kitchenRepository) {
        super(kitchenRepository);
        this.kitchenRepository = kitchenRepository;
    }


    public KitchenArticle add(KitchenArticle kitchenArticle){
        kitchenArticle.setCategory("Mobila");
        kitchenArticle.setSubcategory("Bucatarie");
        kitchenArticle.setCreatedAt(LocalDateTime.now());
        kitchenArticle.setUpdateAt(LocalDateTime.now());
        //kitchenArticle.setUser();

        return super.save(kitchenArticle);
    }
}
