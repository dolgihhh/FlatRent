package higold.by.flatrent.controllers;

import higold.by.flatrent.dto.requests.LogInDTO;
import higold.by.flatrent.dto.requests.SignUpDTO;
import higold.by.flatrent.dto.responses.JwtToken;
import higold.by.flatrent.dto.responses.SimpleMessage;
import higold.by.flatrent.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Validated
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<SimpleMessage> signUp(@Valid @RequestBody SignUpDTO signUpDTO) {
        SimpleMessage message = authService.signUp(signUpDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(message);
    }

    @PostMapping("/log-in")
    public ResponseEntity<JwtToken> logIn(@Valid @RequestBody LogInDTO logInDTO) {
        JwtToken token = authService.logIn(logInDTO);

        return ResponseEntity.status(HttpStatus.OK)
                             .body(token);
    }

//    @RequestMapping("/to-be-redirected")
//    public RedirectView localRedirect() {
//        RedirectView redirectView = new RedirectView();
//        redirectView.setUrl("https://www.youtube.com/");
//        return redirectView;
//    }

}
