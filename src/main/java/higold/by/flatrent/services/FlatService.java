package higold.by.flatrent.services;

import higold.by.flatrent.dto.requests.FlatCreateDTO;
import higold.by.flatrent.dto.responses.FlatDTO;
import higold.by.flatrent.dto.responses.SimpleMessage;
import higold.by.flatrent.entities.Flat;
import higold.by.flatrent.entities.User;
import higold.by.flatrent.enums.AdvStatus;
import higold.by.flatrent.exceptions.NoSuchFlatException;
import higold.by.flatrent.mappers.FlatMapper;
import higold.by.flatrent.repositories.FlatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlatService {

    private final FlatMapper flatMapper;
    private final FlatRepository flatRepository;
    private final UserService userService;

    public SimpleMessage createFlat(FlatCreateDTO flatCreateDTO) {
        User user = userService.getCurrentUser();

        Flat newFlat = flatMapper.flatCreateDTOToFlat(flatCreateDTO);
        newFlat.setUser(user);
        flatRepository.save(newFlat);

        return new SimpleMessage("Successfully created flat");
    }

    public List<FlatDTO> getUserFlats() {
        User user = userService.getCurrentUser();
        List<FlatDTO> userFlatDTOs = new ArrayList<>();

        for (Flat flat : user.getFlats()) {
            userFlatDTOs.add(flatMapper.flatToFlatDTO(flat));
        }

        return userFlatDTOs;
    }

    public FlatDTO getFlat(Long id) {
        User user = userService.getCurrentUser();
        Flat flat = flatRepository.findById(id).orElseThrow(NoSuchFlatException::new);

        if (flat.getUser() != user) {
            throw new NoSuchFlatException();
        }

        return flatMapper.flatToFlatDTO(flat);
    }

    public List<FlatDTO> getUserRentAvailableFlats() {
        User user = userService.getCurrentUser();
        List<FlatDTO> userFlatDTOs = new ArrayList<>();

        for (Flat flat : user.getFlats()) {
            if (flat.getAdvertisement() == null || flat.getAdvertisement()
                                                       .getAdvStatus() == AdvStatus.INACTIVE) {
                userFlatDTOs.add(flatMapper.flatToFlatDTO(flat));
            }
        }

        return userFlatDTOs;
    }
}
