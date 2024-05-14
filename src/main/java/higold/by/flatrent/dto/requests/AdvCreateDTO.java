package higold.by.flatrent.dto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import higold.by.flatrent.enums.RentType;
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
public class AdvCreateDTO {

    @Min(value = 1, message = "Id must be greater than or equal to 1")
    @JsonProperty("flat_id")
    private Long id;

    @JsonProperty("rent_type")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Rent type type cannot be null")
    private RentType rentType;

    @NotBlank(message = "Contact number is required")
    @JsonProperty("contact_number")
    private String contactNumber;

    @NotBlank(message = "Contact name is required")
    @JsonProperty("contact_name")
    private String contactName;

    @NotBlank(message = "Description is required")
    private String description;

    @DecimalMin(value = "1.00", message = "Price must be greater than or equal to 1")
    private BigDecimal price;
}
