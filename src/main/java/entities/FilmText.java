package entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "film_text")
@Getter
@Setter
@ToString
public class FilmText {

    @Id
    @Column(name = "film_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @OneToOne
    @JoinColumn(name = "film_id")
    private Film film;

    private String title;

    @Column(columnDefinition = "text")
    private String description;


}
