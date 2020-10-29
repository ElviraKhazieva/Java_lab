package ru.itis.repositories;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, ID> {

    void save(T entity);
    void update(T entity);
    void delete(T entity);
    T findById(ID id);
    List<T> findAll();

}
