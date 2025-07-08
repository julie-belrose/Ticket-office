package org.example.ticketing.repository;

import java.util.Optional;
import java.util.List;

public interface Repository<T> {

    void save(T obj);

    Optional<T> find(String id);

    List<T> findAll();

    void delete(String id);
}
