package higold.by.flatrent.services;

import higold.by.flatrent.dto.requests.UserUpdateDTO;
import higold.by.flatrent.dto.responses.SimpleMessage;
import higold.by.flatrent.dto.responses.UserDTO;
import higold.by.flatrent.mappers.UserMapper;
import higold.by.flatrent.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import higold.by.flatrent.entities.User;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email)
                                  .orElseThrow(() -> new UsernameNotFoundException(
                                          String.format("User with email '%s' not found", email)));

        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                                                                      user.getPassword(),
                                                                      Collections.emptyList());
    }

    public UserDTO getUser() {
        User user = getCurrentUser();

        return userMapper.userToUserDTO(user);
    }

    public User getCurrentUser() {
        String userEmail = SecurityContextHolder.getContext()
                                                .getAuthentication()
                                                .getName();

        return userRepository.findByEmail(userEmail)
                             .orElseThrow(() -> new UsernameNotFoundException(
                                     String.format("User with email '%s' not found", userEmail)));
    }

    public User getCurrentUserSafely() {
        try {
            return getCurrentUser();
        } catch (UsernameNotFoundException e) {
            return null;
        }
    }

    public SimpleMessage updateUser(UserUpdateDTO userUpdateDTO) {
        User user = getCurrentUser();

        user.setEmail(userUpdateDTO.getEmail());
        if (userUpdateDTO.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userUpdateDTO.getPassword()));
        }
        user.setName(userUpdateDTO.getName());
        user.setPhoneNumber(userUpdateDTO.getPhoneNumber());

        userRepository.save(user);

        return new SimpleMessage("User updated successfully");
    }
}
