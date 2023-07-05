package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Category;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@ThreadSafe
@Repository
@AllArgsConstructor
public class CategoryRepository {
    private final CrudRepository crudRepository;

    public List<Category> findAll() {
      return crudRepository.query("from Category", Category.class);
    }

    public boolean save(Category category) {
        return crudRepository.run(session -> session.persist(category));
    }

    public Optional<Category> findById(int id) {
        return crudRepository.optional("""
            from Category i 
            where i.id = :fId
            """, Category.class, Map.of("fId", id));
    }
}
