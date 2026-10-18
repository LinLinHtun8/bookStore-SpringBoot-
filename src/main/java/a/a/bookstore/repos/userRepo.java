package a.a.bookstore.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import a.a.bookstore.models.appUser;


public interface userRepo extends JpaRepository<appUser, Integer>{

    Optional<appUser> findByName(String name);
    
}
