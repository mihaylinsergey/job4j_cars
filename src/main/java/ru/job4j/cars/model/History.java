package ru.job4j.cars.model;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "post_history")
@Data
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "startAt")
    private LocalDateTime startAt;
    @Column(name = "endAt")
    private LocalDateTime startEnd;
}
