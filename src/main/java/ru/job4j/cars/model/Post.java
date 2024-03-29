package ru.job4j.cars.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "auto_post")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder(builderMethodName = "of")
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    private String text;

    @Builder.Default
    private LocalDateTime created = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "auto_user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @OneToOne
    @JoinColumn(name = "post_history_id")
    private History history;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "file_post_id")
    private Set<File> photo;

    @OneToOne(mappedBy = "post")
    private PriceHistory priceHistory;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "participates",
            joinColumns = {@JoinColumn(name = "post_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    @Builder.Default
    private List<User> participates = new ArrayList<>();

    private boolean sold;
}
