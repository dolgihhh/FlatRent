package higold.by.flatrent.controllers;

import higold.by.flatrent.dto.requests.LogInDTO;
import higold.by.flatrent.dto.requests.UserUpdateDTO;
import higold.by.flatrent.dto.responses.JwtToken;
import higold.by.flatrent.dto.responses.SimpleMessage;
import higold.by.flatrent.dto.responses.UserDTO;
import higold.by.flatrent.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Validated
public class UserController {

    private final UserService userService;


    @GetMapping("")
    public ResponseEntity<UserDTO> getUser() {
        UserDTO user = userService.getUser();

        return ResponseEntity.status(HttpStatus.OK)
                             .body(user);
    }

    @PutMapping("")
    public ResponseEntity<SimpleMessage> updateUser(@Valid @RequestBody UserUpdateDTO userUpdateDTO) {
        SimpleMessage message = userService.updateUser(userUpdateDTO);

        return ResponseEntity.status(HttpStatus.OK)
                             .body(message);
    }

}
