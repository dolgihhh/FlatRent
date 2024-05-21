package higold.by.flatrent.controllers;

import higold.by.flatrent.dto.requests.FlatCreateDTO;
import higold.by.flatrent.dto.requests.FlatUpdateDTO;
import higold.by.flatrent.dto.responses.FlatDTO;
import higold.by.flatrent.dto.responses.SimpleMessage;
import higold.by.flatrent.services.FlatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/flats")
@Validated
public class FlatController {

    private final FlatService flatService;

    @PostMapping("")
    public ResponseEntity<SimpleMessage> createFlat(
            @Valid @RequestBody FlatCreateDTO flatCreateDTO) {
        SimpleMessage message = flatService.createFlat(flatCreateDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(message);
    }

    @GetMapping("/user")
    public ResponseEntity<List<FlatDTO>> getUserFlats() {
        List<FlatDTO> userFlats = flatService.getUserFlats();

        return ResponseEntity.status(HttpStatus.OK)
                             .body(userFlats);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlatDTO> getFlat(@PathVariable Long id) {
        FlatDTO flatDTO = flatService.getFlat(id);

        return ResponseEntity.status(HttpStatus.OK)
                             .body(flatDTO);
    }

    @GetMapping("/user/rent-available")
    public ResponseEntity<List<FlatDTO>> getUserRentAvailableFlats() {
        List<FlatDTO> userFlats = flatService.getUserRentAvailableFlats();

        return ResponseEntity.status(HttpStatus.OK)
                             .body(userFlats);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SimpleMessage> updateFlat(@PathVariable Long id,
                                                    @Valid @RequestBody FlatUpdateDTO flatUpdateDTO) {
        SimpleMessage message = flatService.updateFlat(id, flatUpdateDTO);

        return ResponseEntity.status(HttpStatus.OK)
                             .body(message);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SimpleMessage> deleteFlat(@PathVariable Long id) {
        SimpleMessage message = flatService.deleteFlat(id);

        return ResponseEntity.status(HttpStatus.OK)
                             .body(message);
    }
}
