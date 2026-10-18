package a.a.bookstore.services;

import java.util.List;
import java.util.ListResourceBundle;

import org.springframework.stereotype.Service;

import a.a.bookstore.models.book;
import a.a.bookstore.repos.bookRepo;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class bookService {

    private final bookRepo bookRepo;

    public List<book> getAll() {

        List<book> books = bookRepo.findAll();
        return books;

    }

    public book findBook(Integer id) {
        book book = bookRepo.findById(id).orElse(null);
        return book;
    }

    public void add(book book) {
        bookRepo.save(book);
    }

    public void edit(book book) {
        bookRepo.save(book);
    }

    public void drop(Integer id) {
        book book = bookRepo.findById(id).orElse(null);
        bookRepo.delete(book);
    }

}
