package higold.by.flatrent.controllers;

import higold.by.flatrent.dto.responses.SimpleMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Validated
public class TestController {

    @GetMapping("/test")
    public SimpleMessage testApi() {

        return new SimpleMessage("test");
    }

}
