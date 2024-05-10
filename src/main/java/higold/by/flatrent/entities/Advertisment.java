package higold.by.flatrent.entities;

import higold.by.flatrent.enums.RenovationType;
import higold.by.flatrent.enums.RentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "Advertisments")
@Table(name = "advertisments")
@Data
@AllArgsConstructor
@Builder
public class Advertisment {

    @Id
    @Column(name = "flat_id")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "flat_id")
    private Flat flat;

    @Enumerated(EnumType.STRING)
    @Column(name = "rent_type", nullable = false)
    private RentType rentType;

    @Column(name = "contact_number", nullable = false)
    private String contactNumber;

    @Column(name = "contact_name", nullable = false)
    private String contactName;

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "number_of_views", nullable = false)
    private Integer numberOfViews;

    @Column(name = "creation_date", nullable = false)
    private LocalDate creation_date;


    public Advertisment() {
        this.creation_date = LocalDate.now();
    }
}
