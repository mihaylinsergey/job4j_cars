package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Post;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@ThreadSafe
@Repository
@AllArgsConstructor
public class PostRepository {

    private final CrudRepository crudRepository;

    private static final String TEMPLATE_QUERY = """
            select distinct i from Post i 
            left join fetch i.photo p
            left join fetch i.participates
            left join i.car c
            """;

    public List<Post> findAllPostsForLastDay() {
        return crudRepository.query(TEMPLATE_QUERY + "where created > :fDate",
                Post.class, Map.of("fDate", LocalDateTime.now().minusHours(24)));
    }

    public List<Post> findAllPostsWithPhotos() {
        return crudRepository.query(TEMPLATE_QUERY + "where p IS NOT NULL", Post.class);
    }

    public List<Post> findAllPostsWithBrand(String brand) {
        return crudRepository.query(TEMPLATE_QUERY + "where c.brand.name = :fBrand",
                Post.class, Map.of("fBrand", brand));
    }

    public List<Post> findAll() {
     return crudRepository.query(TEMPLATE_QUERY, Post.class);
    }

    public boolean save(Post post) {
        return crudRepository.run(session -> session.persist(post));
    }

    public Optional<Post> findById(int id) {
        return crudRepository.optional(TEMPLATE_QUERY + "where i.id = :fId",
            Post.class, Map.of("fId", id));
    }

    public boolean update(Post post) {
        return crudRepository.run(session -> session.merge(post));
    }

    public boolean delete(int id) {
        return crudRepository.run("DELETE FROM Post WHERE id = :fId",
                Map.of("fId", id));
    }

    public boolean makeItSold(int id) {
        return crudRepository.run("UPDATE Post i SET i.sold = :fSold WHERE id = :fId",
                Map.of("fSold", true, "fId", id));
    }
}
