package com.example.demo.caching;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class DateController {

    @Cacheable(cacheNames = "myCache", key = "'day'")
    @GetMapping("/what/day")
    public String getDayOfWeek() {
        System.out.println("Getting day of week");
        return LocalDate.now().getDayOfWeek().toString();
    }

    @Cacheable(cacheNames = "myCache", key = "'year'")
    @CacheEvict(cacheNames = "myCache", key = "'day'")
    @GetMapping("/what/year")
    public String getYear() {
        System.out.println("Getting year, clearing day");
        return String.valueOf(LocalDate.now().getYear());
    }
}
