package entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "rental")
@Getter
@Setter
@ToString
public class Rental {

    @Column(name = "rental_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "rental_date")
    private LocalDateTime rentalDate;

    @OneToOne
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;


    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "return_date")
    private LocalDateTime returnDate;

    @OneToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;

    @Column(name = "last_update")
    @UpdateTimestamp
    private LocalDateTime lastUpdate;
}
