package com.fufu.ecommerce.repository;

import java.util.Collection;
import java.util.Optional;

public interface CrudRepository<T> {

    /**
     * Save entity
     */
    void save(T entity);

    /**
     * Save all entities given
     * @param entities
     */
    void saveAll(Collection<T> entities);

    /**
     * Find entity
     * @param <T> entity
     * @return an {@code Optional} item
     */
    Optional<T> find(T t);

    /**
     * Check entity exist
     */
    boolean existsById(T t);

    /**
     *
     * @return Collection All entities
     */
    Collection<T> findAll();

    /**
     *
     * @return Entity count
     */
    Integer count();

    /**
     * Delete entity
     * @param <T> entity
     */
    void delete(T entity);

    /**
     * Delete given entities
     * @param <? extends T> entities
     */
    void deleteAll(Collection<? extends T> entities);

    /**
     * Delete all entities
     */
    void deleteAll();

}
