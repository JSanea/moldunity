package web.app.moldunity.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public class BaseJpaService<T, ID> {
    private final JpaRepository<T, ID> repository;

    public BaseJpaService(JpaRepository<T, ID> repository){
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Optional<T> findById(ID id){
        return repository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<T> findAll(){
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public List<T> findAll(Sort sort){
        return repository.findAll(sort);
    }

    @Transactional(readOnly = true)
    public Page<T> findAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    public T save(T t){
        return repository.save(t);
    }

    public void deleteById(ID id){
        repository.deleteById(id);
    }

    public void delete(T t){
        repository.delete(t);
    }
}


























