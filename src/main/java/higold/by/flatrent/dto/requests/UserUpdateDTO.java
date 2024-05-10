package higold.by.flatrent.dto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDTO {

    @Email(message = "Invalid email format")
    private String email;

    private String password;

    private String name;

    @JsonProperty("phone_number")
    private String phoneNumber;
}
