package com.example.demo.quest;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quest")
public class QuestController {

    @GetMapping("/status")
    public String status(Authentication authentication) {
        return String.format("%s's quest is in progress!", authentication.getName());
    }

    @GetMapping
    public String index(Authentication authentication) {
        return String.format("Greetings %s!", authentication.getName());
    }
}
