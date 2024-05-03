package higold.by.flatrent.dto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import higold.by.flatrent.enums.RenovationType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlatReqDTO {

    @Min(value = 1, message = "Number of rooms must be greater than or equal to 1")
    @JsonProperty("number_of_rooms")
    private Integer numberOfRooms;

    @Column(nullable = false)
    @NotBlank(message = "There must be city")
    private String city;

    @Column(nullable = false)
    @NotBlank(message = "There must be street")
    private String street;

    @JsonProperty("house_number")
    @Min(value = 1, message = "House number must be greater than or equal to 1")
    private Integer houseNumber;

    @JsonProperty("total_area")
    @DecimalMin(value = "4.00", message = "Floor must be greater than or equal to 4")
    private BigDecimal totalArea;

    @JsonProperty("living_space")
    @DecimalMin(value = "4.00", message = "Floor must be greater than or equal to 4")
    private BigDecimal livingSpace;

    @Min(value = 1, message = "Floor must be greater than or equal to 1")
    @NotNull(message = "Credit type cannot be null")
    private Integer floor;

    @JsonProperty("total_floors")
    @Min(value = 1, message = "Total floors must be greater than or equal to 1")
    @NotNull(message = "Credit type cannot be null")
    private Integer totalFloors;

    @JsonProperty("tv")
    @NotNull(message = "TV cannot be null")
    private Boolean hasTV;

    @JsonProperty("internet")
    @NotNull(message = "Internet cannot be null")
    private Boolean hasInternet;

    @JsonProperty("renovation_type")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Renovation type cannot be null")
    private RenovationType renovationType;
}
