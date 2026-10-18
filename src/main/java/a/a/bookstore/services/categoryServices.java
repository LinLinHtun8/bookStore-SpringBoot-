package a.a.bookstore.services;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;

import a.a.bookstore.models.category;
import a.a.bookstore.repos.categoryRepo;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class categoryServices {

    private final categoryRepo categoryRepo;

    public List<category> all() {
        List<category> categories = categoryRepo.findAll();
        return categories;
    }

    public void add(String name) {

        category category = new category();
        category.setName(name);
        categoryRepo.save(category);

    }

    public void edit(Integer id, String name) {

        category category = categoryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't find This Category"));
        if (category != null) {
            category.setName(name);
            categoryRepo.save(category);
        }

    }

    public void drop(Integer id) {
        category category = categoryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't find This Category"));

        if (category != null) {

            categoryRepo.delete(category);
        }
    }

    public category findById(Integer id) {
        category category = categoryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't find the Category"));
        return category;
    }

    public category findByName(String name){
        category category = categoryRepo.findByName(name).orElseThrow();
        return category;
    }

}
