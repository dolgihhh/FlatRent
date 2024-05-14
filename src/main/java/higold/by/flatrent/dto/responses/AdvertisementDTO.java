package higold.by.flatrent.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import higold.by.flatrent.enums.RenovationType;
import higold.by.flatrent.enums.RentType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvertisementDTO {
    private Long id;

//    @JsonProperty("number_of_rooms")
//    private Integer numberOfRooms;
//
//    private String city;
//
//    private String street;
//
//    @JsonProperty("house_number")
//    private Integer houseNumber;
//
//    @JsonProperty("total_area")
//    private BigDecimal totalArea;
//
//    @JsonProperty("living_space")
//    private BigDecimal livingSpace;
//
//    private Integer floor;
//
//    @JsonProperty("total_floors")
//    private Integer totalFloors;
//
//    @JsonProperty("tv")
//    private Boolean hasTV;
//
//    @JsonProperty("internet")
//    private Boolean hasInternet;
//
//    @Enumerated(EnumType.STRING)
//    @JsonProperty("renovation_type")
//    private RenovationType renovationType;

    @Enumerated(EnumType.STRING)
    @JsonProperty("rent_type")
    private RentType rentType;

    @JsonProperty("contact_number")
    private String contactNumber;

    @JsonProperty("contact_name")
    private String contactName;

    private String description;

    private BigDecimal price;

    @JsonProperty("number_of_views")
    private Integer numberOfViews;

    @JsonProperty("creation_date")
    private LocalDate creationDate;

    @JsonProperty("is_favourite")
    private Boolean isFavourite;

    private FlatDTO flat;
}
