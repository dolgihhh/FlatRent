package higold.by.flatrent.controllers;

import higold.by.flatrent.dto.requests.FlatReqDTO;
import higold.by.flatrent.dto.requests.SignUpDTO;
import higold.by.flatrent.dto.responses.SimpleMessage;
import higold.by.flatrent.services.FlatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/flats")
@Validated
public class FlatController {

    private final FlatService flatService;

    @PostMapping("")
    public ResponseEntity<SimpleMessage> createFlat(@Valid @RequestBody FlatReqDTO flatReqDTO) {
        SimpleMessage message = flatService.createFlat(flatReqDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(message);
    }
}
