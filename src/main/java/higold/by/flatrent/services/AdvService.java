package higold.by.flatrent.services;

import higold.by.flatrent.dto.requests.AdvCreateDTO;
import higold.by.flatrent.dto.requests.FavouriteAdvCreateDTO;
import higold.by.flatrent.dto.responses.AdvertisementDTO;
import higold.by.flatrent.dto.responses.SimpleMessage;
import higold.by.flatrent.entities.Advertisement;
import higold.by.flatrent.entities.FavouriteAdvertisement;
import higold.by.flatrent.entities.Flat;
import higold.by.flatrent.entities.User;
import higold.by.flatrent.enums.AdvStatus;
import higold.by.flatrent.exceptions.*;
import higold.by.flatrent.mappers.AdvertisementMapper;
import higold.by.flatrent.repositories.AdvertisementRepository;
import higold.by.flatrent.repositories.FavouriteAdvertisementRepository;
import higold.by.flatrent.repositories.FlatRepository;
import higold.by.flatrent.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdvService {

    private final FlatRepository flatRepository;
    private final AdvertisementMapper advertisementMapper;
    private final AdvertisementRepository advertisementRepository;
    private final FavouriteAdvertisementRepository favouriteAdvertisementRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    public SimpleMessage createAdvertisement(AdvCreateDTO advCreateDTO) {
        User user = userService.getCurrentUser();

        Flat flat =
                flatRepository.findById(advCreateDTO.getId())
                              .orElseThrow(NoSuchFlatException::new);

        if (!user.equals(flat.getUser())) {
            throw new NoSuchFlatException();
        }

        if (flat.getAdvertisement() != null && flat.getAdvertisement()
                                                   .getAdvStatus() == AdvStatus.ACTIVE) {

            throw new AdvertisementDuplicateException();
        } else if (flat.getAdvertisement() != null && flat.getAdvertisement()
                                                          .getAdvStatus() == AdvStatus.INACTIVE) {

            Advertisement currentAdv = flat.getAdvertisement();

            currentAdv.setAdvStatus(AdvStatus.ACTIVE);
            currentAdv.setCreationDate(LocalDate.now());
            currentAdv.setNumberOfViews(0);
            currentAdv.setRentType(advCreateDTO.getRentType());
            currentAdv.setContactNumber(advCreateDTO.getContactNumber());
            currentAdv.setContactName(advCreateDTO.getContactName());
            currentAdv.setDescription(advCreateDTO.getDescription());
            currentAdv.setPrice(advCreateDTO.getPrice());

            advertisementRepository.save(currentAdv);
        } else {
            Advertisement newAdv = advertisementMapper.advCreateDTOToAdv(advCreateDTO);

            newAdv.setUser(user);
            newAdv.setFlat(flat);
            newAdv.setAdvStatus(AdvStatus.ACTIVE);
            newAdv.setCreationDate(LocalDate.now());
            newAdv.setNumberOfViews(0);

            advertisementRepository.save(newAdv);
        }

        return new SimpleMessage("Advertisement created");
    }

    public SimpleMessage addFavouriteAdv(FavouriteAdvCreateDTO favouriteAdvCreateDTO) {
        User user = userService.getCurrentUser();

        Advertisement advertisement =
                advertisementRepository.findById(favouriteAdvCreateDTO.getAdvertisementId())
                              .orElseThrow(NoSuchAdvException::new);

        if (advertisement.getAdvStatus().equals(AdvStatus.INACTIVE)) {
            throw new NoSuchAdvException();
        }

        Optional<FavouriteAdvertisement> favouriteAdvertisement =
                favouriteAdvertisementRepository.findByUserAndAdvertisement(user, advertisement);

        if (favouriteAdvertisement.isPresent()) {
            throw new FavouriteAdvDuplicateException();
        }

        FavouriteAdvertisement newFav = FavouriteAdvertisement.builder()
                                                              .user(user)
                                                              .advertisement(advertisement)
                                                              .build();

        favouriteAdvertisementRepository.save(newFav);

        return new SimpleMessage("Advertisement added to favourite");
    }

    public List<AdvertisementDTO> getUserAdvertisements() {
        User user = userService.getCurrentUser();
        List<Advertisement> userAdvertisements =
                advertisementRepository.findByUserAndAdvStatus(user, AdvStatus.ACTIVE);
        List<AdvertisementDTO> userAdvertisementDTOs = new ArrayList<>();

        for (Advertisement advertisement : userAdvertisements) {
            AdvertisementDTO advDTO = advertisementMapper.advToAdvertisementDTO(advertisement);
            advDTO.setIsFavourite(
                    favouriteAdvertisementRepository.existsByUserAndAdvertisement(user,
                                                                                  advertisement));
            userAdvertisementDTOs.add(advDTO);
        }

        return userAdvertisementDTOs;
    }

    public List<AdvertisementDTO> getFavouriteAdvertisements() {
        User user = userService.getCurrentUser();
        List<FavouriteAdvertisement> favouriteAdvertisements = user.getFavouriteAdvertisements();
        List<AdvertisementDTO> favAdvertisementDTOs = new ArrayList<>();

        for (FavouriteAdvertisement favouriteAdv : favouriteAdvertisements) {
            Advertisement advertisement = favouriteAdv.getAdvertisement();
            if (advertisement.getAdvStatus() == AdvStatus.ACTIVE) {
                AdvertisementDTO advDTO = advertisementMapper.advToAdvertisementDTO(advertisement);
                advDTO.setIsFavourite(true);
                favAdvertisementDTOs.add(advDTO);
            }
        }

        return favAdvertisementDTOs;
    }

    public List<AdvertisementDTO> getAllAdvertisements() {
        User user = userService.getCurrentUserSafely();
        List<Advertisement> advertisements =
                advertisementRepository.findByAdvStatus(AdvStatus.ACTIVE);
        List<AdvertisementDTO> advertisementDTOs = new ArrayList<>();

        for (Advertisement advertisement : advertisements) {
            AdvertisementDTO advDTO = advertisementMapper.advToAdvertisementDTO(advertisement);

            if (user != null) {
                advDTO.setIsFavourite(favouriteAdvertisementRepository
                                              .existsByUserAndAdvertisement(user, advertisement));
            } else {
                advDTO.setIsFavourite(false);
            }
            advertisementDTOs.add(advDTO);
        }

        return advertisementDTOs;
    }

    @Transactional
    public AdvertisementDTO getAdvertisement(Long id) {
        User user = userService.getCurrentUserSafely();
        Advertisement advertisement =
                advertisementRepository.findByIdAndAdvStatus(id, AdvStatus.ACTIVE)
                                       .orElseThrow(NoSuchAdvException::new);

        advertisement.setNumberOfViews(advertisement.getNumberOfViews() + 1);
        advertisementRepository.save(advertisement);

        AdvertisementDTO advDTO = advertisementMapper.advToAdvertisementDTO(advertisement);

        if (user != null) {
            advDTO.setIsFavourite(
                    favouriteAdvertisementRepository.existsByUserAndAdvertisement(user,
                                                                                  advertisement));
        } else {
            advDTO.setIsFavourite(false);
        }

        return advDTO;
    }

    @Transactional
    public SimpleMessage deactivateAdvertisement(Long id) {
        User user = userService.getCurrentUser();
        Advertisement advertisement = advertisementRepository.findById(id)
                                                             .orElseThrow(NoSuchAdvException::new);
        if (advertisement.getUser() != user) {
            throw new NoSuchAdvException();
        }

        if (advertisement.getAdvStatus() == AdvStatus.INACTIVE) {
            throw new AdvertisementAlreadyInactiveException();
        }

        advertisement.setAdvStatus(AdvStatus.INACTIVE);
        advertisementRepository.save(advertisement);

        return new SimpleMessage("Advertisement successfully deactivated");
    }
}
