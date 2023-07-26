package ru.job4j.cars.mapper;

import ru.job4j.cars.dto.FileDto;
import ru.job4j.cars.dto.PostDto;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.model.User;

import java.util.List;

public interface PostMapper {
    Post getPost(Post post, PostDto postDto, User user);

    PostDto getPostDto(Post post);

    Post createEmptyPost();
}
