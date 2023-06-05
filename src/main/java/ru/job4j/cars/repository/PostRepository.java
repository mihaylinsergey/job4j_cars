package ru.job4j.cars.repository;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class PostRepository {

    private final CrudRepository crudRepository;

    public List<Post> findAllPostsForLastDay() {
        return crudRepository.query("from Post as i where i.created >= fDate",
                Post.class, Map.of("fDate", LocalDateTime.now().minusHours(24)));
    }

    public List<Post> findAllPostsWithPhotos() {
        return crudRepository.query("from Post as i where i.photo.size > 0", Post.class);
    }

    public List<Post> findAllPostsWithBrand(String brand) {
        return crudRepository.query("from Post as i where i.car.brand = :fBrand",
                Post.class, Map.of("fBrand", brand));
    }

}
