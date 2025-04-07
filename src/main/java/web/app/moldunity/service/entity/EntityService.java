package web.app.moldunity.service.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EntityService<ID> {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public <T> Optional<T> getById(ID id, Class<T> entity){
        return Optional.ofNullable(entityManager.find(entity, id));
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

    @Transactional(readOnly = true)
    public <T> Long getNumRecords(Class<T> entity){
        return (Long) entityManager.createQuery("select count(x.id) from " + entity.getSimpleName() + " x").getSingleResult();
    }

    @Transactional(readOnly = true)
    public <T> Long getCountOfUserArticles(String username, Class<T> entity){
        return entityManager.createQuery("select count(*) From " + entity.getSimpleName() + " x where x.username = ?1", Long.class)
                .setParameter(1, username)
                .getSingleResult();
    }

    @Transactional
    public <T> T add(T t, String eId, Class<T> entity){
        entityManager.persist(t);
        return entityManager.createQuery("select x From " + entity.getSimpleName() + " x where x.eId = ?1", entity)
                .setParameter(1, eId)
                .getSingleResult();
    }

    @Transactional
    public <T> Boolean removeById(ID id, Class<T> entity){
        T t = entityManager.find(entity, id);
        if(null == t) return false;
        entityManager.remove(t);
        return entityManager.find(entity, id) == null;
    }

    @Transactional
    public <T> void removeByIdAndUsername(ID id, String username, Class<T> entity){
        try {
            T t = entityManager.createQuery("select x from " + entity.getSimpleName() + " x where x.id = ?1 and x.username = ?2", entity)
                    .setParameter(1, id)
                    .setParameter(2, username)
                    .getSingleResult();
            if(null != t)
                entityManager.remove(t);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Transactional
    public <T> void remove(T t){
        entityManager.remove(t);
    }
}
















