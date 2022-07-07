package net.proselyte.springbootdemo.controller;

import net.proselyte.springbootdemo.model.User;
import net.proselyte.springbootdemo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping()
public class AdminController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("users", userServiceImpl.allUsers());
        return "user-list";
    }

    @GetMapping("/admin/user-create")
    public String createUserForm(User user) {
        return "user-create";
    }
    @PostMapping("/admin/user-create")
    public String createUser(User user) {
        userServiceImpl.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping ("/admin/user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userServiceImpl.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model) {
        User user = userServiceImpl.findUserById(id);
        model.addAttribute("user", user);
        return "user-update";
    }
    @PostMapping("/admin/user-update")
    public String updateUser(User user) {
        userServiceImpl.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/user-show/{id}")
    public String userShow(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userServiceImpl.findUserById(id));
        return "show-user";
    }
}
