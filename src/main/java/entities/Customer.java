package entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "customer")
@Getter
@Setter
@ToString
public class Customer {

    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;


    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;


    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "active", columnDefinition = "BIT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean active;


    @Column(name = "create_date")
    @CreationTimestamp
    private LocalDateTime dateTime;


    @Column(name = "last_update")
    @UpdateTimestamp
    private LocalDateTime lastUpdate;


    @OneToMany
    @JoinColumn(name = "customer_id")
    private Set<Rental> rentals;
}
