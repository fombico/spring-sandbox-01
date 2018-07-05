package com.example.demo.aop;

import com.example.demo.exception.TeapotException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/unsecure")
public class HelloController {

    @GetMapping("/hello")
    public String hello(@RequestParam(defaultValue = "world") String name) {
        return String.format("Hello, %s!", name);
    }

    @GetMapping("/teapot")
    public String teapot() {
        throw new TeapotException();
    }
}
