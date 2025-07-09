package org.example.ticketing.repository;

import org.example.ticketing.domain.exception.DeleteException;
import org.example.ticketing.domain.exception.NotFoundException;
import org.example.ticketing.domain.exception.SaveException;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class MapRepository<T> implements Repository<T> {

    private final Map<String, T> store = new HashMap<>();
    private final Function<T, String> idExtractor;

    public MapRepository(Function<T, String> idExtractor) {
        this.idExtractor = idExtractor;
    }

    @Override
    public void save(T obj) {
        try {
            store.put(idExtractor.apply(obj), obj);
        } catch (Exception e) {
            throw new SaveException(e.getMessage());
        }
    }

    @Override
    public Optional<T> find(String id) {
        T val = store.get(id);
        if (val == null) throw new NotFoundException(id);
        return Optional.of(val);
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void delete(String id) {
        if (store.remove(id) == null) {
            throw new DeleteException("ID " + id);
        }
    }
}
