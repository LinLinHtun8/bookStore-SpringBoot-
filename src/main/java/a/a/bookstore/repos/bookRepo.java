package a.a.bookstore.repos;

import java.lang.foreign.Linker.Option;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import a.a.bookstore.models.book;
import java.util.List;
import a.a.bookstore.models.category;


public interface bookRepo extends JpaRepository<book, Integer>{

    List<book> findAllByCategory(category category);
    
}
