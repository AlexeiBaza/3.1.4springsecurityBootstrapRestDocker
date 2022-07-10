package net.proselyte.springbootdemo.controller;

import net.proselyte.springbootdemo.model.User;
import net.proselyte.springbootdemo.service.RoleService;
import net.proselyte.springbootdemo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping()
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("users",
                userService.readAll());
        return "user-list";
    }

    @GetMapping("/admin/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model) {
        User user = userService.readById(id).get();
        model.addAttribute("user", user);
        return "user-update";
    }
    @PostMapping("/admin/user-update")
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam(value = "userRoles", required = false) String[] roles) {
        userService.update(user, roles);
        return "redirect:/admin";
    }









    @GetMapping("/admin/user-create")
    public String createUserForm(User user) {
        return "user-create";
    }

    @PostMapping("/admin/user-create")
    public String createUser(@ModelAttribute("user") User user,
                             @RequestParam(value = "userRoles", required = false) String[] roles) {
        userService.create(user, roles);
        return "redirect:/admin";
    }

    @GetMapping ("/admin/user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(userService.readById(id).get());
        return "redirect:/admin";
    }



    @GetMapping("/admin/user-show/{id}")
    public String userShow(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.readById(id));
        return "show-user";
    }
}
