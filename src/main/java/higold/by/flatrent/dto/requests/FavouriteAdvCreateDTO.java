package higold.by.flatrent.dto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavouriteAdvCreateDTO {

    @Min(value = 1, message = "Id must be greater than or equal to 1")
    @JsonProperty("advertisement_id")
    private Long advertisementId;
}
