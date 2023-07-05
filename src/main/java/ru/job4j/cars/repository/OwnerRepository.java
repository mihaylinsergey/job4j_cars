package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Owner;

import java.util.List;

@ThreadSafe
@Repository
@AllArgsConstructor
public class OwnerRepository {

    private final CrudRepository crudRepository;

    public List<Owner> findAll() {
        return crudRepository.query("from Owner", Owner.class);
    }

    public boolean save(Owner owner) {
        return crudRepository.run(session -> session.persist(owner));
    }
}
