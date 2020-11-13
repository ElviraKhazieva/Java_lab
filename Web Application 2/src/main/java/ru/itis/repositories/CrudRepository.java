package ru.itis.repositories;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, ID> {
    void save(T entity);
    void delete(ID id);
    //void delete(T entity);
    void update(T entity);
    Optional<T> findById(ID id);
    List<T> findAll();
    List<T> findAll(int page, int size);

}

