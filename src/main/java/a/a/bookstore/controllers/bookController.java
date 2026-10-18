package a.a.bookstore.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import a.a.bookstore.dtos.bookDto;
import a.a.bookstore.models.book;
import a.a.bookstore.models.category;
import a.a.bookstore.services.bookService;
import a.a.bookstore.services.categoryServices;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import a.a.bookstore.utils.save;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("books")
@AllArgsConstructor
public class bookController {

    private final bookService bookService;
    private final categoryServices categoryServices;

    @GetMapping("")
    public String getAllBook(org.springframework.ui.Model model) {
        List<book> books = bookService.getAll();
        model.addAttribute("books", books);
        return "book/all";
    }

    @GetMapping("add")
    public String addBookString(Model model) {
        List<category> categories = categoryServices.all();
        model.addAttribute("cates", categories);
        return "book/add";
    }

    @PostMapping("add")
    public String postMethodName(@ModelAttribute bookDto bookDto) {
        book book = new book();
        category category = categoryServices.findById(bookDto.getCategoryId());
        String imageName = save.saveFile(bookDto.getCoverImage());
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setCategory(category);
        book.setCoverImage(imageName);
        book.setDescription(bookDto.getDescription());
        book.setPrice(bookDto.getPrice());
        book.setPublicationDate(bookDto.getPublicationDate());
        book.setStock(bookDto.getStock());
        bookService.add(book);
        return "redirect:/books";
    }

    @GetMapping("edit/{id}")
    public String getedit(@PathVariable Integer id, Model model) {

        book book = bookService.findBook(id);
        List<category> categories = categoryServices.all();
        model.addAttribute("book", book);
        model.addAttribute("cates", categories);
        return "book/edit";
    }

    @PostMapping("edit/{id}")
    public String postedit(@ModelAttribute bookDto bookDto, @PathVariable Integer id) {
        book book = bookService.findBook(id);
        category category = categoryServices.findById(bookDto.getCategoryId());
        String imageName = save.saveFile(bookDto.getCoverImage());
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setCategory(category);
        book.setCoverImage(imageName);
        book.setDescription(bookDto.getDescription());
        book.setPrice(bookDto.getPrice());
        book.setPublicationDate(bookDto.getPublicationDate());
        book.setStock(bookDto.getStock());
        bookService.add(book);
        return "redirect:/books";
    }

    @GetMapping("drop/{id}")
    public String deleteString(@PathVariable Integer id) {
        bookService.drop(id);
        return "redirect:/books";
    }
    

}
