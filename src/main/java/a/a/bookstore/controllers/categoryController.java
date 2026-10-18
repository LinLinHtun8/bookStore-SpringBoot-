package a.a.bookstore.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import a.a.bookstore.models.category;
import a.a.bookstore.services.categoryServices;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("cates")
@AllArgsConstructor
public class categoryController {

    private final categoryServices categoryServices;

    @GetMapping("")
    public String allString(Model model) {
        List<category> categories = categoryServices.all();
        model.addAttribute("cates", categories);
        return "category/all";
    }

    @GetMapping("/add")
    public String getadd() {
        return "category/add";
    }

    @PostMapping("/add")
    public String postadd(@RequestParam String categoryname) {
        categoryServices.add(categoryname);
        return "redirect:/cates";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        category category = categoryServices.findById(id);
        model.addAttribute("cate", category);
        return "category/edit";
    }
    

    @PostMapping("/edit/{id}")
    public String edString(@PathVariable Integer id, @RequestParam String name) {

        categoryServices.edit(id, name);
        return "redirect:/cates";

    }

    @GetMapping("/drop/{id}")
    public String drop(@PathVariable Integer id) {

        categoryServices.drop(id);
        return "redirect:/cates";
        
    }
    

    
    
    
    
    


    
}
