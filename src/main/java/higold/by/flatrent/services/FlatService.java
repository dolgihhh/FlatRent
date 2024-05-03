package higold.by.flatrent.services;

import higold.by.flatrent.dto.requests.FlatReqDTO;
import higold.by.flatrent.dto.responses.SimpleMessage;
import higold.by.flatrent.entities.Flat;
import higold.by.flatrent.entities.User;
import higold.by.flatrent.mappers.FlatMapper;
import higold.by.flatrent.repositories.FlatRepository;
import higold.by.flatrent.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlatService {

    private final FlatMapper flatMapper;
    private final FlatRepository flatRepository;
    private final UserRepository userRepository;

    public SimpleMessage createFlat(FlatReqDTO flatReqDTO) {
        String userEmail = SecurityContextHolder.getContext()
                                                .getAuthentication()
                                                .getName();
        System.out.println(userEmail);

        User user = userRepository.findByEmail(userEmail)
                                  .orElseThrow(() -> new UsernameNotFoundException(
                                          String.format("User with email '%s' not found",
                                                        userEmail)));

        Flat newFlat = flatMapper.flatReqDTOToFlat(flatReqDTO);
        newFlat.setUser(user);
        flatRepository.save(newFlat);

        return new SimpleMessage("Successfully created flat");
    }
}
