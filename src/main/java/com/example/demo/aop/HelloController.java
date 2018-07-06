package com.example.demo.aop;

import com.example.demo.exception.TeapotException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.Locale;

@Slf4j
@RestController
@RequestMapping("/unsecure")
public class HelloController {

    @GetMapping("/hello")
    public String hello(@RequestParam(defaultValue = "world") String name, Locale locale, UriComponentsBuilder componentsBuilder) {
        // use header 'Accept-Language' to change locale, e.g. fr-CA (maps to fr_CA)
        log.info("url: {}, Locale: {}", componentsBuilder.toUriString(), locale);
        return String.format("Hello, %s!", name);
    }

    @GetMapping("/teapot")
    public String teapot() {
        throw new TeapotException();
    }

    @PostMapping("/ice-cream")
    public String iceCream(@RequestBody @Valid Flavor flavor) {
        log.info("Log does not appear if Flavor is invalid, handled by spring");
        return String.format("Here you go, one %s ice cream", flavor.getFlavor());
    }

    @PostMapping("/ice-cream2")
    public String iceCream2(@RequestBody @Valid Flavor flavor, Errors errors) {
        log.info("Log appears if Flavor is invalid");

        if (errors.hasErrors()) {
            log.info("Detected {} errors", errors.getErrorCount());
            throw new TeapotException();
        }

        return String.format("Here you go, one %s ice cream", flavor.getFlavor());
    }
}
