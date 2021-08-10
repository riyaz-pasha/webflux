package com.example.webflux.asyncPOC;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class DummyController extends AsyncController {

    @Autowired
    DummyService service;

    @GetMapping(value = "/asyncService/items", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getItemsAsync() {
        ResponseEntity<?> response;

        response = runAsync("getItem", () -> service.getItemsWithDelay());

        log.info("Controller --------- response -- " + response);
        return response;
    }
}
