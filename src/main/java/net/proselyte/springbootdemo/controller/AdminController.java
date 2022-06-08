package net.proselyte.springbootdemo.controller;

import net.proselyte.springbootdemo.model.User;
import net.proselyte.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@Controller
@RequestMapping()
public class AdminController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user-list";
    }




    @GetMapping("/admin/user-create")
    public String createUserForm(User user) {
        return "user-create";
    }
    @PostMapping("/admin/user-create")
    public String createUser(User user) {
        userService.saveUser(user);
        return "redirect:/user-list";
    }



    @GetMapping("/admin/user-delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteById(id);
        return "redirect:/user-list";
    }


    @GetMapping("/admin/user-update/{id}")
    public String updateUserForm(@PathVariable("id") int id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user-update";
    }
    @PostMapping("/admin/user-update")
    public String updateUser(User user) {
        userService.saveUser(user);//метод save сам определяет обговление или создание новой сущности
        return "redirect:/user-list";
    }



}
