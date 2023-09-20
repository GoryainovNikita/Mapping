package entities;

import helper.Feature;
import helper.Rating;
import helper.RatingConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Entity
@Table(name = "film")
@Getter
@Setter
public class Film {
    @Id
    @Column(name = "film_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    private String title;

    @Column(columnDefinition = "text")
    @Type(type = "text")
    private String description;;

    @Column(name = "release_year", columnDefinition = "year")
    private Short releaseYear;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    @ManyToOne
    @JoinColumn(name = "original_language_id")
    private Language originalLanguage;

    @Column(name = "rental_duration")
    private Byte rentalDuration;

    @Column(name = "rental_rate")
    private BigDecimal rentalRate;

    private Short length;

    @Column(name = "replacement_cost")
    private BigDecimal replacementCost;

    @Column(columnDefinition = "enum('G', 'PG', 'PG-13', 'R', 'NC-17')")
    @Convert(converter = RatingConverter.class)
    private Rating rating;

    @Column(name = "special_features", columnDefinition = "set('Trailers', 'Commentaries', 'Deleted Scenes', 'Behind the Scenes')")
    private String specialFeatures;

    @Column(name = "last_update")
    @UpdateTimestamp
    private LocalDateTime lastUpdate;


    @ManyToMany
    @JoinTable(name = "film_category",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    )
    private Set<Category> categorySet;

    @ManyToMany
    @JoinTable(name = "film_actor",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "actor_id")
    )
    private Set<Actor> actors;

    @OneToMany
    @JoinColumn(name = "film_id")
    private Set<Inventory> inventories;


    public Set<Feature> getSpecialFeatures() {
        if(isNull(specialFeatures) || specialFeatures.isEmpty()){
            return null;
        }
        Set<Feature> features = new HashSet<>();
        Arrays.stream(specialFeatures.split(",")).forEach(e -> features.add(Feature.getFeatureByValue(e)));
        features.remove(null);
        return features;
    }

    public void setSpecialFeatures(Set<Feature> specialFeatures) {
        if(specialFeatures == null){
            this.specialFeatures = null;
        }
        else {
            this.specialFeatures = specialFeatures.stream().map(Feature::getValue).collect(Collectors.joining(","));
        }
    }
}
