package ru.job4j.cars.repository;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class PostRepository {

    private final CrudRepository crudRepository;

    public List<Post> findAllPostsForLastDay() {
        return crudRepository.query("from Post where created > :fDate",
                Post.class, Map.of("fDate", LocalDateTime.now().minusHours(24)));
    }

    public List<Post> findAllPostsWithPhotos() {
        return crudRepository.query("from Post as i where i.photo.size() > 0", Post.class);
    }

    public List<Post> findAllPostsWithBrand(String brand) {
        return crudRepository.query("from Post as i where i.car.brand = :fBrand",
                Post.class, Map.of("fBrand", brand));
    }

    public List<Post> findAll() {
        return crudRepository.query("from Post", Post.class);
    }

    public boolean save(Post post) {
        return crudRepository.run(session -> session.persist(post));
    }

    public Optional<Post> findById(int id) {
        return crudRepository.optional("from Post as i where i.id = :fId",
                Post.class, Map.of("fId", id));
    }

    public boolean update(Post post) {
        return crudRepository.run("UPDATE Post SET text = :fText WHERE id = :fId",
                Map.of("fText", post.getText(), "fId", post.getId()));
    }

    public boolean delete(int id) {
        return crudRepository.run("DELETE FROM Post WHERE id = :fId",
                Map.of("fId", id));
    }
}
