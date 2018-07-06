package com.example.demo.filter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilterController {

    @GetMapping("filter")
    public String filter() {
        return "Filter";
    }
}
