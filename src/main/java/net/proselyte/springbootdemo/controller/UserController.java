package net.proselyte.springbootdemo.controller;

import lombok.Data;
import net.proselyte.springbootdemo.model.User;
import net.proselyte.springbootdemo.service.RoleService;
import net.proselyte.springbootdemo.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Optional;

@Data
@Controller
public class UserController {
    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String greeting() {
        return "greeting.html";
    }

    @GetMapping("/user/userPage")
    public String getUser(Model model, Principal principal) {
        Optional<User> userOptional = Optional.of((User) ((Authentication) principal).getPrincipal());
        model.addAttribute("simpleUser", (User) ((Authentication) principal).getPrincipal());
        return "userPage.html";
    }

}
