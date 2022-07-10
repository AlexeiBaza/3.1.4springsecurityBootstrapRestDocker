package net.proselyte.springbootdemo.controller;

import lombok.Data;
import net.proselyte.springbootdemo.model.User;
import net.proselyte.springbootdemo.service.RoleService;
import net.proselyte.springbootdemo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

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
        return "greeting";
//        return "redirect:/login";
    }

    @GetMapping("/user")
    public String findAll(Model model) {
        List<User> users = userService.readAll();
        model.addAttribute("users", users);
        return "user-list";
    }

}
