package ru.job4j.cars.model;

import javax.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "PRICE_HISTORY")
@Data
public class PriceHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private BigDecimal before;
    private BigDecimal after;
    private LocalDateTime created;
    @OneToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
