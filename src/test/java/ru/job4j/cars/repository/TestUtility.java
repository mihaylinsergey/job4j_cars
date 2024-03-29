package ru.job4j.cars.repository;

import lombok.Data;
import org.hibernate.SessionFactory;
import ru.job4j.cars.configuration.HibernateConfiguration;
import ru.job4j.cars.model.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
public class TestUtility {

    private final SessionFactory sf = new HibernateConfiguration().sf();
    private final CrudRepository crudRepository = new CrudRepository(sf);
    private final PostRepository postRepository = new PostRepository(crudRepository);
    private final BrandRepository brandRepository = new BrandRepository(crudRepository);
    private final EngineRepository engineRepository = new EngineRepository(crudRepository);
    private final CategoryRepository categoryRepository = new CategoryRepository(crudRepository);
    private final CarBodyRepository carBodyRepository = new CarBodyRepository(crudRepository);
    private final CarModelRepository carModelRepository = new CarModelRepository(crudRepository);
    private final CarColorRepository carColorRepository = new CarColorRepository(crudRepository);
    private final HistoryRepository historyRepository = new HistoryRepository(crudRepository);
    private final CarRepository carRepository = new CarRepository(crudRepository);
    private final OwnerRepository ownerRepository = new OwnerRepository(crudRepository);
    private final FileRepository fileRepository = new FileRepository(crudRepository);
    private final PriceHistoryRepository priceHistoryRepository = new PriceHistoryRepository(crudRepository);

    public Brand createBrandAndSave(String num) {
        Brand brand = new Brand();
        brand.setName("brandName" + num);
        brandRepository.save(brand);
        return brand;
    }

    public Engine createEngineAndSave(String num) {
        Engine engine = new Engine();
        engine.setName("engineName" + num);
        engine.setVolume(Double.parseDouble(num) / 10);
        engine.setPower(Integer.parseInt(num) * 100);
        engineRepository.save(engine);
        return engine;
    }

    public Owner createOwnerAndSave(String num) {
        Owner owner = new Owner();
        owner.setName("ownerName" + num);
        ownerRepository.save(owner);
        return owner;
    }

    public Category createCategory(String num) {
        return categoryRepository.findById(Integer.parseInt(num)).get();
    }

    public CarBody createCarBody(String num) {
        return carBodyRepository.findById(Integer.parseInt(num)).get();
    }

    public CarModel createCarModelAndSave(String num) {
        CarModel carModel = new CarModel();
        carModel.setName("carModel" + num);
        carModelRepository.save(carModel);
        return carModel;
    }

    public CarColor createCarColorAndSave(String num) {
        CarColor carColor = new CarColor();
        carColor.setName("carColor" + num);
        carColorRepository.save(carColor);
        return carColor;
    }

    public Car createCarAndSave(String num) {
        Car car = new Car();
        car.setBrand(createBrandAndSave(num));
        car.setEngine(createEngineAndSave(num));
        car.setOwner(createOwnerAndSave(num));
        car.setCategory(createCategory(num));
        car.setCarBody(createCarBody(num));
        car.setYear(Integer.parseInt(num) + 2000);
        car.setMileage(Integer.parseInt(num) * 1000);
        car.setCarModel(createCarModelAndSave(num));
        car.setColor(createCarColorAndSave(num));
        carRepository.save(car);
        return car;
    }

    public History createHistoryAndSave(String num) {
        History history = new History();
        LocalDateTime date = switch (num) {
            case "1" -> LocalDateTime.now().minusHours(30);
            default -> LocalDateTime.now();
        };
        history.setStartAt(date);
        historyRepository.save(history);
        return history;
    }

    public Set<File> createFileAnfSave(String num) {
        Set<File> files = new HashSet<>();
        if (!"1".contains(num)) {
            files.add(new File("fileName" + num, "filePath" + num));
            files.forEach(fileRepository::save);
        }
        return files;
    }

    public Post createPostAndSave(String num) {
        LocalDateTime date = switch (num) {
            case "1" -> LocalDateTime.now().minusHours(30);
            default -> LocalDateTime.now();
        };
        Post post = new Post();
        post.setText("description" + num);
        post.setCreated(date);
        post.setCar(createCarAndSave(num));
        post.setHistory(createHistoryAndSave(num));
        post.setPhoto(createFileAnfSave(num));
        postRepository.save(post);
        return post;
    }

    public PriceHistory createPriceHistoryAndSave(String num) {
        LocalDateTime date = switch (num) {
            case "1" -> LocalDateTime.now().minusHours(30);
            default -> LocalDateTime.now();
        };
        PriceHistory priceHistory = new PriceHistory();
        priceHistory.setBefore(new BigDecimal(Integer.parseInt(num) * 100000));
        priceHistory.setCreated(date);
        priceHistory.setPost(createPostAndSave(num));
        priceHistoryRepository.save(priceHistory);
        return priceHistory;
    }
}
