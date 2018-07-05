package com.example.demo.di;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MagicGardenController {

    private MagicBean magicBean1;
    private MagicBean magicBean2;
    private String magicBean3Name;

    // demonstrate referencing beans by name, using SpEL to inject a bean's property
    public MagicGardenController(MagicBean magicBean1, MagicBean magicBean2, @Value("#{magicBean3.name}") String magicBean3Name) {
        this.magicBean1 = magicBean1;
        this.magicBean2 = magicBean2;
        this.magicBean3Name = magicBean3Name;
    }

    @GetMapping("garden/water")
    public String water1() {
        return String.format("%s is growing!!", magicBean1.getName());
    }

    @GetMapping("garden/plant")
    public String plant2() {
        return String.format("%s was planted!!", magicBean2.getName());
    }

    @GetMapping("garden/search")
    public String search3() {
        return String.format("Found %s!!", magicBean3Name);
    }
}
