package a.a.bookstore.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import a.a.bookstore.models.Role;
import java.util.Optional;


public interface roleRepository  extends JpaRepository<Role, Integer>{    

    Optional<Role>  findById(Integer id);
    
}
