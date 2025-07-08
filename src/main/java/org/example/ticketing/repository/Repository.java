package org.example.ticketing.repository;

import java.util.Optional;
import java.util.List;

public interface Repository<T> {

    // Ajoute ou remplace l’objet (clé = id)
    void save(T obj);

    // Renvoie l’objet s’il existe, sinon Optional.empty()
    Optional<T> find(String id);

    // Renvoie une liste immuable de tous les objets
    List<T> findAll();

    // Supprime l’objet associé à l’id (silencieux si id inconnu)
    void delete(String id);
}
