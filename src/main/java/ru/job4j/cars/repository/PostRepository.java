package ru.job4j.cars.repository;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Post;

import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class PostRepository {

    private final CrudRepository crudRepository;

    public List<Post> findAllPostsForLastDay() {
        return crudRepository.query("from Post as i where i.created >= curdate()", Post.class);
    }

    public List<Post> findAllPostsWithPhotos() {
        return crudRepository.query("from Post as i where i.photo is not null", Post.class);
    }

    public List<Post> findAllPostsWithBrand(String brand) {
        return crudRepository.query("from Post as i where i.text like '%':fBrand'%'",
                Post.class, Map.of("fBrand", brand));
    }

}
