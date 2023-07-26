package ru.job4j.cars.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.job4j.cars.dto.PostDto;
import ru.job4j.cars.model.*;
import ru.job4j.cars.repository.CarModelRepository;
import ru.job4j.cars.service.BrandService;
import ru.job4j.cars.service.CarBodyService;
import ru.job4j.cars.service.CarColorService;
import ru.job4j.cars.service.CategoryService;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;

@Component
@AllArgsConstructor
public class PostMapperImpl implements PostMapper {

    private final BrandService brandService;
    private final CategoryService categoryService;

    private final CarBodyService carBodyService;
    private final CarColorService carColorService;
    private final CarModelRepository carModelRepository;

    @Override
    public Post getPost(Post post, PostDto postDto, User user) {
        Car car = post.getCar();
        car.setBrand(brandService.findById(parseInt(postDto.getBrandName())).get());
        car.setCategory(categoryService.findById(parseInt(postDto.getCategoryName())).get());
        car.setCarBody(carBodyService.findById(Integer.parseInt(postDto.getCarBodyName())).get());
        car.setCarModel(carModelRepository.findById(parseInt(postDto.getCarModelName())).get());
        car.setColor(carColorService.findById(parseInt(postDto.getColorName())).get());
        car.setMileage(parseInt(postDto.getMileage()));
        car.setYear(parseInt(postDto.getYear()));

        Engine engine = car.getEngine();
        engine.setPower(parseInt(postDto.getPower()));
        engine.setVolume(parseDouble(postDto.getVolume()));
        engine.setName(postDto.getEngineName());

        if (user != null) {
            Owner owner = car.getOwner();
            owner.setName(user.getLogin());
            owner.setUser(user);
            post.setUser(user);
        }

        post.setText(postDto.getDescription());
        return post;
    }

    @Override
    public PostDto getPostDto(Post post) {
        PostDto postDto = PostDto.of()
                .id(post.getId())
                .brandName(post.getCar().getBrand().getName())
                .carModelName(post.getCar().getCarModel().getName())
                .colorName(post.getCar().getColor().getName())
                .categoryName(post.getCar().getCategory().getName())
                .colorName(post.getCar().getColor().getName())
                .carBodyName(post.getCar().getCarBody().getName())
                .engineName(post.getCar().getEngine().getName())
                .volume(valueOf(post.getCar().getEngine().getVolume()))
                .power(valueOf(post.getCar().getEngine().getPower()))
                .year(valueOf(post.getCar().getYear()))
                .mileage(valueOf(post.getCar().getMileage()))
                .price(valueOf(post.getPriceHistory().getBefore()))
                .description(post.getText())
                .userName(post.getUser().getLogin())
                .phoneNumber(post.getUser().getPhoneNumber())
                .sold(post.isSold())
                .build();
        List<String> photo = List.of("1");
        if (!post.getPhoto().isEmpty()) {
            photo = post.getPhoto().stream()
                    .map(e -> valueOf(e.getId()))
                    .collect(Collectors.toList());
        }
        postDto.setListPhotoId(photo);
        return postDto;
    }

    @Override
    public Post createEmptyPost() {
        Car car = Car.of()
                .brand(new Brand())
                .engine(new Engine())
                .owner(new Owner())
                .category(new Category())
                .carBody(new CarBody())
                .carModel(new CarModel())
                .color(new CarColor())
                .build();
        return Post.of()
                .user(new User())
                .car(car)
                .history(new History())
                .photo(new HashSet<>())
                .build();
    }
}
