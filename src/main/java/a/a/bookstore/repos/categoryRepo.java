package a.a.bookstore.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import a.a.bookstore.models.category;
import java.util.List;


public interface categoryRepo extends JpaRepository<category, Integer> {

    Optional<category> findByName(String name);
    
}
