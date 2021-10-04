package com.example.webflux.appCache.secondary;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SecondaryController {

    @GetMapping("/numberToWord/{number}")
    public String getWordForGivenNumber(@PathVariable("number") int number) {
        log.info("Returning Word for number {}", number);
        if (number == 1) return "One";
        if (number == 2) return "Two";
        if (number == 3) return "Three";
        if (number == 4) return "Four";
        if (number == 5) return "Five";
        if (number == 6) return "Six";
        if (number == 7) return "Seven";
        if (number == 8) return "Eight";
        if (number == 9) return "Nine";
        if (number == 0) return "Zero";
        return null;
    }
}
