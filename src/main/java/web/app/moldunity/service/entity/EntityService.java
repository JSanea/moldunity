package web.app.moldunity.service.entity;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EntityService {
    private final EntityManager entityManager;

    @Autowired
    public EntityService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    public <T> T getById(Long id, Class<T> entity){
        return entityManager.find(entity, id);
    }

    @Transactional(readOnly = true)
    public <T> List<T> getAll(Class<T> entity){
        return entityManager.createQuery("from " + entity.getSimpleName(), entity).getResultList();
    }

    @Transactional(readOnly = true)
    public <T> List<T> getPageSortedByRepublishedAtDesc(Integer page, Integer offset, Class<T> entity){
        return entityManager
                .createQuery("select x From " + entity.getSimpleName() + " x order by x.republishedAt desc limit ?1 offset ?2", entity)
                .setParameter(1, page)
                .setParameter(2, offset)
                .getResultList();
    }

    @Transactional
    public <T> T add(T t, Class<T> entity){
        entityManager.persist(t);
        return entityManager
                .createQuery("select x from " + entity.getSimpleName() + " x order by x.id desc limit 1", entity)
                .getSingleResult();
    }

    @Transactional(readOnly = true)
    public <T> Long getNumRecords(Class<T> entity){
        return (Long) entityManager.createQuery("select count(x.id) from " + entity.getSimpleName() + " x").getSingleResult();
    }

    @Transactional
    public <T> void removeById(Long id, Class<T> entity){
        entityManager.remove(entityManager.find(entity, id));
    }
}
















