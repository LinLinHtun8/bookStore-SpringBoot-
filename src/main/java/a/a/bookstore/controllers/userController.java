package a.a.bookstore.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import a.a.bookstore.configs.webSecurityConfig;
import a.a.bookstore.dtos.userDto;
import a.a.bookstore.models.Role;
import a.a.bookstore.models.appUser;
import a.a.bookstore.services.roleServices;
import a.a.bookstore.services.userService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("users")
@AllArgsConstructor
public class userController {

    private final userService userService;
    private final roleServices roleServices;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String loginPage() {
        return "user/login";
    }

    @GetMapping("register")
    public String registerPage(Model model) {
        appUser user = new appUser();
        model.addAttribute("user", user);
        return "user/register";
    }

    @PostMapping("register")
    // public String registerUser(@ModelAttribute userDto userDto) {
    // appUser user = new appUser();
    // user.setName(userDto.getName());
    // user.setEmail(userDto.getEmail());
    // user.setPassword(passwordEncoder.encode(userDto.getPassword()));
    // userService.add(user);
    // return "user/login";
    // }
    public String registerUser(@Valid @ModelAttribute("user") appUser newUser, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "user/register";
        } else {
            appUser user = new appUser();
            user.setName(newUser.getName());
            user.setEmail(newUser.getEmail());
            user.setPassword(passwordEncoder.encode(newUser.getPassword()));
            userService.add(user);
            return "user/login";
        }

    }

    @GetMapping("")
    public String allUsers(Model model) {
        List<appUser> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "user/all";
    }

    @GetMapping("edit/{id}")
    public String getEditPage(@PathVariable Integer id, Model model) {
        appUser user = userService.findByUserID(id);
        List<Role> roles = roleServices.getAll();
        List<Role> remainRoles = roles.stream().filter(role -> !user.getRoles().contains(role)).toList();
        model.addAttribute("user", user);
        model.addAttribute("roles", remainRoles);
        return "user/edit";
    }

    @GetMapping("addRole/{userId}/{roleId}")
    public String addRoleToUser(@PathVariable Integer userId, @PathVariable Integer roleId) {
        appUser user = userService.findByUserID(userId);
        Role role = roleServices.findById(roleId);
        Set<Role> roles = user.getRoles();
        roles.add(role);
        user.setRoles(roles);
        userService.saveUser(user);
        return "redirect:/users/edit/" + userId;
    }

    @GetMapping("removeRole/{userId}/{roleId}")
    public String removeRoleFromUser(@PathVariable Integer userId, @PathVariable Integer roleId) {
        appUser user = userService.findByUserID(userId);
        Role role = roleServices.findById(roleId);
        Set<Role> roles = user.getRoles();
        roles.remove(role);
        user.setRoles(roles);
        userService.saveUser(user);
        return "redirect:/users/edit/" + userId;
    }

}
