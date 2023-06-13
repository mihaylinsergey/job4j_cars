package ru.job4j.cars.repository;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.configuration.HibernateConfiguration;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.File;
import ru.job4j.cars.model.Post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class PostRepositoryTest {

    private static SessionFactory sf;

    private final CrudRepository crudRepository = new CrudRepository(sf);

    private final PostRepository postRepository = new PostRepository(crudRepository);

    @BeforeAll
    public static void init() {
        sf = new HibernateConfiguration().sf();
    }

    @AfterAll
    public static void close() {
        sf.close();
    }

    @Test
    void findAllPostsForLastDay() {
        Post post1 = new Post();
        Post post2 = new Post();
        post1.setCreated(LocalDateTime.now().minusHours(5));
        post2.setCreated(LocalDateTime.now().minusHours(30));
        postRepository.save(post1);
        postRepository.save(post2);
        var result = postRepository.findAllPostsForLastDay();
        assertThat(result, is(List.of(post1)));
    }

    @Test
    void findAllPostsWithPhotos() {
        Post post1 = new Post();
        Post post2 = new Post();
        post1.setPhoto(List.of(new File("test", "test")));
        postRepository.save(post1);
        postRepository.save(post2);
        var result = postRepository.findAllPostsWithPhotos();
        assertThat(result, is(List.of(post1)));
    }

    @Test
    void findAllPostsWithBrand() {
        Post post1 = new Post();
        Post post2 = new Post();
        Car car = new Car();
        car.setBrand("BMW");
        post1.setCar(car);
        postRepository.save(post1);
        postRepository.save(post2);
        var result = postRepository.findAllPostsWithBrand("BMW");
        assertThat(result, is(List.of(post1)));
    }

    @Test
    void findAll() {
        Post post1 = new Post();
        Post post2 = new Post();
        post1.setText("test1");
        post2.setText("test2");
        postRepository.save(post1);
        postRepository.save(post2);
        var result = postRepository.findAll();
        assertThat(result, is(List.of(post1, post2)));
    }

    @Test
    void whenSaveAndGetTheSame() {
        Post post = new Post();
        post.setText("test1");
        postRepository.save(post);
        var result = postRepository.findById(post.getId());
        assertThat(result.get().getText(), is(post.getText()));
    }

    @Test
    void update() {
        Post post = new Post();
        post.setText("test1");
        postRepository.save(post);
        post.setText("test2");
        postRepository.update(post);
        assertThat(postRepository.findById(post.getId()).get().getText(), is("test2"));
    }

    @Test
    void whenDelete() {
        Post post1 = new Post();
        Post post2 = new Post();
        post1.setText("test1");
        post2.setText("test2");
        postRepository.save(post1);
        postRepository.save(post2);
        int id = post1.getId();
        postRepository.delete(id);
        assertThat(postRepository.findById(id), is(Optional.empty()));
    }
}