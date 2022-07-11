package net.proselyte.springbootdemo.controller;

import net.proselyte.springbootdemo.model.Role;
import net.proselyte.springbootdemo.model.User;
import net.proselyte.springbootdemo.service.RoleService;
import net.proselyte.springbootdemo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping()
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(path = "/admin")
    public String userList(Model model) {
        model.addAttribute("users",
                userService.readAll());
        return "user-list.html";
    }

    @GetMapping(path = "/admin/user-create")
    public String createUserForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user-create.html";
    }

    @PostMapping(path = "/admin/user-create")
    public String createUser(@ModelAttribute("user") User user, HttpServletRequest request) {
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));

        String[] userRoles = request.getParameterValues("userRoles");
        List<Role> list = new ArrayList<>();
        for(String role: userRoles) {
            list.add(roleService.findByRoleName(role).get());
        }
        user.setRoles(list);
        userService.create(user);
        return "redirect:/admin";
    }

    @GetMapping (path = "/admin/user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(userService.readById(id).get());
        return "redirect:/admin";
    }

    @GetMapping(path = "/admin/user-show/{id}")
    public String userShow(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.readById(id));
        return "show-user.html";
    }

    @GetMapping(path = "/admin/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model) {
        User user = userService.readById(id).get();
        model.addAttribute("user", user);
        return "user-update.html";
    }

    @PostMapping(path = "/admin/user-update/{id}")
    public String updateUser(@ModelAttribute("user") User user,
                             @PathVariable("id") long id,
                             HttpServletRequest request) {

        if (request.getParameter("firstName").isEmpty()) {
            user.setFirstName(userService.readById(id).get().getFirstName());
        }

        String[] userRoles = request.getParameterValues("userRoles");
        if (userRoles == null) {
            user.setRoles(userService.readById(id).get().getRoles());
        } else {
            List<Role> list = new ArrayList<>();
            for (String role: userRoles) {
                list.add(roleService.findByRoleName(role).get());
            }
            user.setRoles(list);
        }

        if (request.getParameter("password").isEmpty()) {
            user.setPassword(userService.readById(id).get().getPassword());
            userService.update(user);
        } else {
            user.setPassword(request.getParameter("password"));
            userService.update(user);
        }
        return "redirect:/admin";
    }
}
