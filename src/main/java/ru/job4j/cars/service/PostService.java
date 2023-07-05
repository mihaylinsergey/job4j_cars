package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.cars.dto.FileDto;
import ru.job4j.cars.dto.PostDto;
import ru.job4j.cars.model.*;
import ru.job4j.cars.repository.PostRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

@ThreadSafe
@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final BrandService brandService;
    private final EngineService engineService;
    private final CategoryService categoryService;
    private final CarBodyService carBodyService;
    private final CarModelService carModelService;
    private final CarColorService carColorService;
    private final HistoryService historyService;
    private final CarService carService;
    private final OwnerService ownerService;
    private final FileService fileService;

    private final PriceHistoryService priceHistoryService;

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
        var createdDate = LocalDateTime.now();

        Brand brand = new Brand();
        brand.setName(postDto.getBrandName());
        brandService.save(brand);

        Engine engine = new Engine();
        engine.setName(postDto.getEngineName());
        engine.setVolume(Double.parseDouble(postDto.getVolume()));
        engine.setPower(parseInt(postDto.getPower()));
        engineService.save(engine);

        Owner owner = new Owner();
        owner.setName(user.getLogin());
        ownerService.save(owner);

        Category category = categoryService.findById(Integer.parseInt(postDto.getCategoryName())).get();

        CarBody carBody = carBodyService.findById(Integer.parseInt(postDto.getCarBodyName())).get();

        CarModel carModel = new CarModel();
        carModel.setName(postDto.getCarModelName());
        carModelService.save(carModel);

        CarColor carColor = new CarColor();
        carColor.setName(postDto.getColor());
        carColorService.save(carColor);

        Car car = new Car();
        car.setBrand(brand);
        car.setEngine(engine);
        car.setOwner(owner);
        car.setCategory(category);
        car.setCarBody(carBody);
        car.setYear(parseInt(postDto.getYear()));
        car.setMileage(parseInt(postDto.getMileage()));
        car.setCarModel(carModel);
        car.setColor(carColor);
        carService.save(car);

        History history = new History();
        history.setStartAt(createdDate);
        historyService.save(history);

        Set<File> photo = null;
        if (!fileDto.get(0).getName().isEmpty()) {
            photo = fileDto
                    .stream()
                    .map(x -> fileService.save(new FileDto(x.getName(), x.getContent())))
                    .collect(Collectors.toSet());
        }

        Post post = new Post();
        post.setText(postDto.getDescription());
        post.setCreated(createdDate);
        post.setUser(user);
        post.setCar(car);
        post.setHistory(history);
        post.setPhoto(photo);
        postRepository.save(post);

        PriceHistory priceHistory = new PriceHistory();
        priceHistory.setBefore(new BigDecimal(postDto.getPrice()));
        priceHistory.setCreated(createdDate);
        priceHistory.setPost(post);
        priceHistoryService.save(priceHistory);
        return true;
    }

    public Optional<PostDto> findById(int id) {
        var post = postRepository.findById(id);
        return Optional.ofNullable(createPostDto(List.of(post.get())).get(0));

    }

    public boolean update(PostDto postDto) {
        Post post = postRepository.findById(postDto.getId()).get();
        Car car = post.getCar();
        Brand brand = car.getBrand();
        brand.setName(postDto.getBrandName());
        brandService.update(brand);
        Engine engine = car.getEngine();
        engine.setPower(parseInt(postDto.getPower()));
        engine.setVolume(Double.parseDouble(postDto.getVolume()));
        engine.setName(postDto.getEngineName());
        engineService.update(engine);
        Category category = categoryService.findById(Integer.parseInt(postDto.getCategoryName())).get();
        car.setCategory(category);
        CarBody carBody = carBodyService.findById(Integer.parseInt(postDto.getCarBodyName())).get();
        car.setCarBody(carBody);
        CarModel carModel = car.getCarModel();
        carModel.setName(postDto.getCarModelName());
        carModelService.update(carModel);
        CarColor carColor = car.getColor();
        carColor.setName(postDto.getColor());
        carColorService.update(carColor);
        car.setMileage(parseInt(postDto.getMileage()));
        car.setYear(parseInt(postDto.getYear()));
        carService.update(car);
        post.setText(postDto.getDescription());
        PriceHistory priceHistory = post.getPriceHistory();
        priceHistory.setBefore(new BigDecimal(postDto.getPrice()));
        priceHistoryService.update(priceHistory);
        return postRepository.update(post);
    }

    public boolean delete(int id) {
        return postRepository.delete(id);
    }

    private List<PostDto> createPostDto(List<Post> posts) {
        return posts.stream()
                .map(x -> {
                    PostDto postDto = new PostDto();
                    postDto.setId(x.getId());
                    postDto.setCarModelName(x.getCar().getCarModel().getName());
                    postDto.setBrandName(x.getCar().getBrand().getName());
                    postDto.setCategoryName(x.getCar().getCategory().getName());
                    postDto.setColor(x.getCar().getColor().getName());
                    postDto.setCarBodyName(x.getCar().getCarBody().getName());
                    postDto.setEngineName(x.getCar().getEngine().getName());
                    postDto.setVolume(String.valueOf(x.getCar().getEngine().getVolume()));
                    postDto.setPower(String.valueOf(x.getCar().getEngine().getPower()));
                    postDto.setYear(String.valueOf(x.getCar().getYear()));
                    postDto.setMileage(String.valueOf(x.getCar().getMileage()));
                    postDto.setPrice(String.valueOf(x.getPriceHistory().getBefore()));
                    postDto.setDescription(x.getText());
                    postDto.setPrice(x.getPriceHistory().getBefore().toString());
                    List<String> photo = List.of("1");
                    if (!x.getPhoto().isEmpty()) {
                        photo = x.getPhoto().stream()
                                .map(e -> String.valueOf(e.getId()))
                                .collect(Collectors.toList());
                    }
                    postDto.setListPhotoId(photo);
                    postDto.setUserName(x.getUser().getLogin());
                    postDto.setPhoneNumber(x.getUser().getPhoneNumber());
                    postDto.setSold(x.isSold());
                    return postDto;
                })
                .collect(Collectors.toList());
    }

    public boolean makeItSold(int id) {
        return postRepository.makeItSold(id);
    }
}
