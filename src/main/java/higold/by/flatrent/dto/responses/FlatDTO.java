package higold.by.flatrent.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import higold.by.flatrent.enums.RenovationType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlatDTO {

    private Long id;

    @JsonProperty("number_of_rooms")
    private Integer numberOfRooms;

    private String city;

    private String street;

    @JsonProperty("house_number")
    private Integer houseNumber;

    @JsonProperty("total_area")
    private BigDecimal totalArea;

    @JsonProperty("living_space")
    private BigDecimal livingSpace;

    private Integer floor;

    @JsonProperty("total_floors")
    private Integer totalFloors;

    @JsonProperty("tv")
    private Boolean hasTV;

    @JsonProperty("internet")
    private Boolean hasInternet;

    @Enumerated(EnumType.STRING)
    @JsonProperty("renovation_type")
    private RenovationType renovationType;
}
