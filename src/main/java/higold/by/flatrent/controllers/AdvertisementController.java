package higold.by.flatrent.controllers;

import higold.by.flatrent.dto.requests.AdvCreateDTO;
import higold.by.flatrent.dto.requests.AdvUpdateDTO;
import higold.by.flatrent.dto.requests.FavouriteAdvCreateDTO;
import higold.by.flatrent.dto.responses.AdvertisementDTO;
import higold.by.flatrent.dto.responses.SimpleMessage;
import higold.by.flatrent.services.AdvService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/advertisements")
@Validated
public class AdvertisementController {

    private final AdvService advService;

    @PostMapping("")
    public ResponseEntity<SimpleMessage> createAdvertisement(@Valid @RequestBody
                                                             AdvCreateDTO advCreateDTO) {
        SimpleMessage message = advService.createAdvertisement(advCreateDTO);


        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(message);
    }

    @PostMapping("/favourite")
    public ResponseEntity<SimpleMessage> addFavouriteAdv(
            @Valid @RequestBody FavouriteAdvCreateDTO favouriteAdvCreateDTO) {
        SimpleMessage message = advService.addFavouriteAdv(favouriteAdvCreateDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(message);
    }

    @GetMapping("/user")
    public ResponseEntity<List<AdvertisementDTO>> getUserAdvertisements() {
        List<AdvertisementDTO> userAdvertisements = advService.getUserAdvertisements();

        return ResponseEntity.status(HttpStatus.OK)
                                    .body(userAdvertisements);
    }

    @GetMapping("/favourite")
    public ResponseEntity<List<AdvertisementDTO>> getFavouriteAdvertisements() {
        List<AdvertisementDTO> favouriteAdvertisements = advService.getFavouriteAdvertisements();

        return ResponseEntity.status(HttpStatus.OK)
                             .body(favouriteAdvertisements);
    }

    @GetMapping("")
    public ResponseEntity<List<AdvertisementDTO>> getAllAdvertisements() {
        List<AdvertisementDTO> allAdvertisements = advService.getAllAdvertisements();

        return ResponseEntity.status(HttpStatus.OK)
                             .body(allAdvertisements);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdvertisementDTO> getAdvertisement(@PathVariable Long id) {
        AdvertisementDTO advertisement = advService.getAdvertisement(id);

        return ResponseEntity.status(HttpStatus.OK)
                             .body(advertisement);
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<SimpleMessage> deactivateAdvertisement(@PathVariable Long id) {
        SimpleMessage message = advService.deactivateAdvertisement(id);

        return ResponseEntity.status(HttpStatus.OK)
                             .body(message);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SimpleMessage> updateAdvertisement(@PathVariable Long id,
                                                             @Valid @RequestBody
                                                             AdvUpdateDTO advUpdateDTO) {
        SimpleMessage message = advService.updateAdvertisement(id, advUpdateDTO);

        return ResponseEntity.status(HttpStatus.OK)
                             .body(message);
    }

    @DeleteMapping("/favourite/{id}")
    public ResponseEntity<SimpleMessage> deleteFavouriteAdvertisement(@PathVariable Long id) {
        SimpleMessage message = advService.deleteFavouriteAdvertisement(id);

        return ResponseEntity.status(HttpStatus.OK)
                             .body(message);
    }
}
