package ru.job4j.cars.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "auto_post")
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String text;
    private LocalDateTime created;
    @ManyToOne
    @JoinColumn(name = "auto_user_id")
    private User user;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "price_history_id")
    private PriceHistory priceHistory;
}
