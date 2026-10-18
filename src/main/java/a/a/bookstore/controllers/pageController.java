package a.a.bookstore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class pageController {

    @GetMapping("/")
    public String getMethodName() {
        return "index";
    }
    
    
}
