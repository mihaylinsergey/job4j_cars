package ru.job4j.cars.repository;

import org.junit.jupiter.api.*;
import ru.job4j.cars.model.*;
import java.util.List;
import java.util.Optional;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class PostRepositoryTest {

    private static TestUtility utility;

    @BeforeAll
    public static void init() {
        utility = new TestUtility();
    }

    @AfterEach
    public void clear() {
         for (Post post : utility.getPostRepository().findAll()) {
             utility.getPostRepository().delete(post.getId());
         }
    }

     @AfterAll
    public static void close() {
         utility.getSf().close();
    }

    @Test
    void findAllPostsForLastDay() {
        Post oldPost = utility.createPostAndSave("1");
        Post newPost = utility.createPostAndSave("2");
        var result = utility.getPostRepository().findAllPostsForLastDay();
        assertThat(result, is(List.of(newPost)));
    }

    @Test
    void findAllPostsWithPhotos() {
        Post post1 = utility.createPostAndSave("1");
        Post post2 = utility.createPostAndSave("2");
        var result = utility.getPostRepository().findAllPostsWithPhotos();
        assertThat(result, is(List.of(post2)));
    }

    @Test
    void findAllPostsWithBrand() {
        Post post1 = utility.createPostAndSave("1");
        var result = utility.getPostRepository().findAllPostsWithBrand("brandName1");
        assertThat(result, is(List.of(post1)));
    }

    @Test
    void findAll() {
        Post post = utility.createPostAndSave("1");
        var result = utility.getPostRepository().findAll();
        assertThat(result, is(List.of(post)));
    }

    @Test
    void update() {
        Post post = utility.createPostAndSave("1");
        post.setText("testNew");
        utility.getPostRepository().update(post);
        assertThat(utility.getPostRepository().findById(post.getId()).get().getText(), is("testNew"));
    }

    @Test
    void whenDelete() {
        Post post = utility.createPostAndSave("1");
        int id = post.getId();
        utility.getPostRepository().delete(id);
        assertThat(utility.getPostRepository().findById(id), is(Optional.empty()));
    }
}