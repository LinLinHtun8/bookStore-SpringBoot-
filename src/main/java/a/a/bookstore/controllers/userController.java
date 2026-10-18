package a.a.bookstore.controllers;

import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import a.a.bookstore.configs.webSecurityConfig;
import a.a.bookstore.dtos.userDto;
import a.a.bookstore.models.Role;
import a.a.bookstore.models.appUser;
import a.a.bookstore.services.roleServices;
import a.a.bookstore.services.userService;

import org.springframework.web.bind.annotation.PostMapping;



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
    public String registeString() {
        return "user/register";
    }

    @PostMapping("register")
    public String registerString(@ModelAttribute userDto userDto) {
        appUser user = new appUser();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userService.add(user);        
        return "user/login";
    }
    
    @GetMapping("")
    public String allString() {
        return "user/all";
    }
    
    
    

}
