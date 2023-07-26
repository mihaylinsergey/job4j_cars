package ru.job4j.cars.repository;


import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Brand;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@ThreadSafe
@Repository
@AllArgsConstructor
public class BrandRepository {

    private final CrudRepository crudRepository;

    public List<Brand> findAll() {
        return crudRepository.query("from Brand", Brand.class);
    }

    public boolean save(Brand brand) {
        return crudRepository.run(session -> session.persist(brand));
    }

    public boolean update(Brand brand) {
        return crudRepository.run(session -> session.merge(brand));
    }

    public Optional<Brand> findById(int id) {
        return crudRepository.optional("""
            from Brand i 
            where i.id = :fId
            """, Brand.class, Map.of("fId", id));
    }
}
