package higold.by.flatrent.entities;

import higold.by.flatrent.enums.RenovationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity(name = "Flats")
@Table(name = "flats")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Flat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(mappedBy = "flat", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Advertisment advertisment;

    @OneToMany(mappedBy = "flat", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FavouriteAdvertisment> favouriteAdvertisments;

    @Column(name = "number_of_rooms", nullable = false)
    private Integer numberOfRooms;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String street;

    @Column(name = "house_number", nullable = false)
    private Integer houseNumber;

    @Column(name = "total_area", nullable = false)
    private BigDecimal totalArea;

    @Column(name = "living_space", nullable = false)
    private BigDecimal livingSpace;

    @Column(nullable = false)
    private Integer floor;

    @Column(name = "total_floors", nullable = false)
    private Integer totalFloors;

    @Column(name = "tv", nullable = false)
    private Boolean hasTV;

    @Column(name = "internet", nullable = false)
    private Boolean hasInternet;

    @Enumerated(EnumType.STRING)
    @Column(name = "renovation_type", nullable = false)
    private RenovationType renovationType;

}
