package web.app.moldunity.service.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EntityService {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public <T> T getById(Long id, Class<T> entity){
        return entityManager.find(entity, id);
    }

    @Transactional(readOnly = true)
    public <T> T getByEId(String eId, Class<T> entity){
        return entityManager.createQuery("select x From " + entity.getSimpleName() + " x where x.eId = ?1", entity)
                .setParameter(1, eId)
                .getSingleResult();
    }

    @Transactional(readOnly = true)
    public <T> List<T> getAll(Class<T> entity){
        return entityManager.createQuery("from " + entity.getSimpleName(), entity).getResultList();
    }

    @Transactional(readOnly = true)
    public <T> List<T> getFavorite(String username, Class<T> entity, String favoriteField){
        return entityManager
                .createQuery("select f From " + entity.getSimpleName() + " f " +
                "left join f." + favoriteField + " "  +
                "where element(f." + favoriteField + ").user.username = ?1", entity)
                .setParameter(1, username)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public <T> List<T> getPageSortedByRepublishedAtDesc(Integer limit, Integer offset, Class<T> entity, String subcategory){
        return entityManager
                .createQuery("select x From " + entity.getSimpleName() +
                        " x  where x.subcategory = ?1 order by x.republishedAt desc limit ?2 offset ?3", entity)
                .setParameter(1, subcategory)
                .setParameter(2, limit)
                .setParameter(3, offset)
                .getResultList();
    }

    @Transactional
    public <T> T add(T t, String eId, Class<T> entity){
        entityManager.persist(t);
        return entityManager.createQuery("select x From " + entity.getSimpleName() + " x where x.eId = ?1", entity)
                .setParameter(1, eId)
                .getSingleResult();
    }

    @Transactional(readOnly = true)
    public <T> Long getNumRecords(Class<T> entity){
        return (Long) entityManager.createQuery("select count(x.id) from " + entity.getSimpleName() + " x").getSingleResult();
    }

    @Transactional
    public <T> Boolean removeById(Long id, Class<T> entity){
        T t = entityManager.find(entity, id);
        if(null == t) return false;
        entityManager.remove(t);
        return entityManager.find(entity, id) == null;
    }
}
















