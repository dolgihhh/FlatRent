package higold.by.flatrent.dto.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogInDTO {

    @Email(message = "Invalid email format")
    @NotBlank(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;
}
