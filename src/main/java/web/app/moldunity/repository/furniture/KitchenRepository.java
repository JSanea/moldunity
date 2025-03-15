package web.app.moldunity.repository.furniture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.app.moldunity.entity.furniture.kitchen.KitchenFurniture;

@Repository
public interface KitchenRepository extends JpaRepository<KitchenFurniture, Long> {
}
