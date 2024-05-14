package higold.by.flatrent.controllers;

import higold.by.flatrent.dto.responses.SimpleMessage;
import higold.by.flatrent.dto.responses.TestResp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Validated
public class TestController {

    @GetMapping("/test")
    public ResponseEntity<List<TestResp>> testApi() {
        List<TestResp> listResp= new ArrayList<>();
        TestResp testResp1 = new TestResp();
        testResp1.setName("Loh");
        List<String> a = new ArrayList<>();
        a.add("imgur/smak234");
        a.add("imgur/12lmd");
        testResp1.setPhotos(a);
        TestResp testResp2 = new TestResp();
        testResp2.setName("Loh");
        List<String> b = new ArrayList<>();
        b.add("imgur/33");
        b.add("imgur/55");
        testResp2.setPhotos(b);
        listResp.add(testResp1);
        listResp.add(testResp2);

        return ResponseEntity.status(HttpStatus.OK)
                             .body(listResp);
    }

}
