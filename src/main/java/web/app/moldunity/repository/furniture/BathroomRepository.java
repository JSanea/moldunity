package web.app.moldunity.repository.furniture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.app.moldunity.entity.furniture.bathroom.BathroomFurniture;

@Repository
public interface BathroomRepository extends JpaRepository<BathroomFurniture, Long> {
}
