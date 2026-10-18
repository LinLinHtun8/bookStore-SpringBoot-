package a.a.bookstore.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import a.a.bookstore.models.book;

public interface bookRepo extends JpaRepository<book, Integer>{
    
}
