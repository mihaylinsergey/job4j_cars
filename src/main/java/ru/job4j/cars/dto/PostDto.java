package ru.job4j.cars.dto;

import lombok.Data;
import java.util.List;

@Data
public class PostDto {

    private int id;
    private String brandName;
    private String carModelName;
    private String categoryName;
    private String color;
    private String carBodyName;
    private String engineName;
    private String volume;
    private String power;
    private String year;
    private String mileage;
    private String price;
    private String description;
    private List<String> listPhotoId;
    private String userName;
    private String phoneNumber;
    private boolean sold;
}
