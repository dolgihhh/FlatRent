package higold.by.flatrent.services;

import higold.by.flatrent.dto.requests.LogInDTO;
import higold.by.flatrent.dto.requests.SignUpDTO;
import higold.by.flatrent.dto.responses.JwtToken;
import higold.by.flatrent.dto.responses.SimpleMessage;
import higold.by.flatrent.entities.User;
import higold.by.flatrent.exceptions.UserAlreadyExistsException;
import higold.by.flatrent.repositories.UserRepository;
import higold.by.flatrent.utils.JwtGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtGenerator jwtGenerator;

    @Transactional
    public SimpleMessage signUp(SignUpDTO signUpDTO) {
        if (userRepository.existsByEmail(signUpDTO.getEmail())) {
            throw new UserAlreadyExistsException();
        }

        User newUser = User.builder()
                           .email(signUpDTO.getEmail())
                           .password(passwordEncoder.encode(signUpDTO.getPassword()))
                           .name(signUpDTO.getName())
                           .phoneNumber(signUpDTO.getPhoneNumber())
                           .build();

        userRepository.save(newUser);

        return new SimpleMessage("Successfully signed up");
    }

    public JwtToken logIn(LogInDTO logInDTO) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(logInDTO.getEmail(),
                                                        logInDTO.getPassword()));

        User user = userRepository.findByEmail(logInDTO.getEmail())
                                  .orElseThrow();

        return new JwtToken(jwtGenerator.generateToken(user));
    }

}
