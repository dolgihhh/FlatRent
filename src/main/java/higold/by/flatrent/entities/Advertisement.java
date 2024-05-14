package higold.by.flatrent.entities;

import higold.by.flatrent.enums.AdvStatus;
import higold.by.flatrent.enums.RentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "Advertisments")
@Table(name = "advertisments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Advertisement {

    @Id
    @Column(name = "flat_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flat_id", nullable = false)
    @MapsId
    private Flat flat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "advertisement", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FavouriteAdvertisement> favouriteAdvertisements;

    @Enumerated(EnumType.STRING)
    @Column(name = "rent_type", nullable = false)
    private RentType rentType;

    @Enumerated(EnumType.STRING)
    @Column(name = "adv_status", nullable = false)
    private AdvStatus advStatus;

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
    private LocalDate creationDate;
}
