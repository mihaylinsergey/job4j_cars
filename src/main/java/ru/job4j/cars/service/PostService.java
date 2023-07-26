package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.cars.dto.FileDto;
import ru.job4j.cars.dto.PostDto;
import ru.job4j.cars.mapper.PostMapper;
import ru.job4j.cars.model.*;
import ru.job4j.cars.repository.PostRepository;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@ThreadSafe
@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final EngineService engineService;
    private final HistoryService historyService;
    private final CarService carService;
    private final OwnerService ownerService;
    private final FileService fileService;

    private final PriceHistoryService priceHistoryService;

    private final PostMapper postMapper;

    public List<PostDto> findAllPostsForLastDay() {
        return createPostDto(postRepository.findAllPostsForLastDay());
    }

    public List<PostDto> findAllPostsWithPhotos() {
        return createPostDto(postRepository.findAllPostsWithPhotos());
    }

    public List<PostDto> findAllPostsWithBrand(String brand) {
        return createPostDto(postRepository.findAllPostsWithBrand(brand));

    }

    public List<PostDto> findAll() {
        return createPostDto(postRepository.findAll());
    }

    public boolean save(PostDto postDto, List<FileDto> fileDto, User user) {
        Post emptyPost = postMapper.createEmptyPost();
        Post post = postMapper.getPost(emptyPost, postDto, user);
        engineService.save(post.getCar().getEngine());
        ownerService.save(post.getCar().getOwner());
        carService.save(post.getCar());
        historyService.save(post.getHistory());
        PriceHistory priceHistory = new PriceHistory();
        priceHistory.setBefore(new BigDecimal(postDto.getPrice()));
        post.setPhoto(getFile(fileDto));
        post.setPriceHistory(priceHistory);
        postRepository.save(post);
        priceHistory.setPost(post);
        priceHistoryService.save(priceHistory);
        return true;
    }

    public Optional<PostDto> findById(int id) {
        var post = postRepository.findById(id);
        return Optional.ofNullable(createPostDto(List.of(post.get())).get(0));

    }

    public boolean update(PostDto postDto, List<FileDto> fileDto) {
        Post oldPost = postRepository.findById(postDto.getId()).get();
        Post newPost = postMapper.getPost(oldPost, postDto, null);
        engineService.update(newPost.getCar().getEngine());
        carService.update(newPost.getCar());
        PriceHistory priceHistory = newPost.getPriceHistory();
        priceHistory.setBefore(new BigDecimal(postDto.getPrice()));
        newPost.setPhoto(getFile(fileDto));
        newPost.setPriceHistory(priceHistory);
        postRepository.update(newPost);
        priceHistory.setPost(newPost);
        return priceHistoryService.update(priceHistory);
    }

    public boolean delete(int id) {
        return postRepository.delete(id);
    }

    private List<PostDto> createPostDto(List<Post> posts) {
        return posts.stream()
                .map(x -> postMapper.getPostDto(x))
                .collect(Collectors.toList());
    }

    public boolean makeItSold(int id) {
        return postRepository.makeItSold(id);
    }

    private Set<File> getFile(List<FileDto> fileDto) {
        Set<File> photo = null;
        if (!fileDto.get(0).getName().isEmpty()) {
            photo = fileDto
                    .stream()
                    .map(x -> fileService.save(new FileDto(x.getName(), x.getContent())))
                    .collect(Collectors.toSet());
        }
        return photo;
    }
}
