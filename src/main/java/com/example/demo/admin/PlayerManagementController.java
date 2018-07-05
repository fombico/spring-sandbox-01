package com.example.demo.admin;

import com.example.demo.user.UserService;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
@RolesAllowed("ADMIN") // Also forces login, in addition to what's in SecurityConfig
@RequestMapping("/admin/management/players")
public class PlayerManagementController {

    private UserService userService;

    public PlayerManagementController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public PlayerList getPlayers() {
        System.out.println("automatically picks up @Roles Allowed");
        return new PlayerList(this.userService.getPlayers());
    }

    @PreAuthorize("#secret == 'p@ssw0rd' && hasRole('ROLE_ADMIN')")
    @GetMapping("secret/{secret}")
    public String getSecret(@PathVariable String secret) {
        System.out.println("@PreAuthorize ignores @RolesAllowed, need to add role check as well");
        return "Secret to all the things";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostAuthorize("1 == 2")
    @GetMapping("super-secret")
    public String getSuperSecret() {
        System.out.println("Running super secret check only after logged in as ADMIN, then returns 403 due to @PostAuthorize");
        return "There is no such thing";
    }

}
