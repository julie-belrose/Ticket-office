package org.example.ticketing.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class MapRepository<T> implements Repository<T> {

    // ─── FIELDS ────────────────────────────────────────────
    private final Map<String, T> store = new HashMap<>();
    private final Function<T, String> idExtractor;   // ex. Client::getId

    // ─── CONSTRUCTOR ──────────────────────────────────────
    public MapRepository(Function<T, String> idExtractor) {
        this.idExtractor = idExtractor;
    }

    // ─── CRUD METHODS ─────────────────────────────────────
    @Override
    public void save(T obj) {
        String id = idExtractor.apply(obj);
        store.put(id, obj);
    }

    @Override
    public Optional<T> find(String id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void delete(String id) {
        store.remove(id);
    }
}
