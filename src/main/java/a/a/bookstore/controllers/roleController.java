package a.a.bookstore.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import a.a.bookstore.models.Role;
import a.a.bookstore.services.roleServices;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/roles")
@AllArgsConstructor
public class roleController {

    private final roleServices roleServices;

    @GetMapping("")
    public String all(org.springframework.ui.Model model) {
        List<Role> roles = roleServices.getAll();
        model.addAttribute("roles", roles);
        return "role/all";
    }
    

    @GetMapping("/add")
    public String add(){
        return "role/add";
    }

    @PostMapping("/add")
    public String add(@RequestParam String rolename) {
        roleServices.add( rolename);        
        return "redirect:/roles";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Role role = roleServices.findById(id);
        model.addAttribute("role", role);   
        return "role/edit";
    }
    

    @PostMapping("/edit/{id}")
    public String edit(@RequestParam String rolename, @PathVariable Integer id) {
        roleServices.edit(id, rolename);        
        return "redirect:/roles";
    }

    @GetMapping("/delete/{id}")
    public String getMethodName(@PathVariable Integer id) {
        roleServices.delete(id);    
        return "redirect:/roles";
    }
    
    
    
    
    
}
