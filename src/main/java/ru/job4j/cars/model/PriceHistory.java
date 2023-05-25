package ru.job4j.cars.model;

import javax.persistence.*;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Table(name = "PRICE_HISTORY")
@Data
public class PriceHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private BigInteger before;
    private BigInteger after;
    private LocalDateTime created;
}
