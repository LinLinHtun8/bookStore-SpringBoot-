package a.a.bookstore.services;

import java.util.List;


import org.springframework.stereotype.Service;

import a.a.bookstore.models.Role;
import a.a.bookstore.repos.roleRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class roleServices {
    
    private final roleRepository roleRepo;


    public List<Role> getAll(){
        List<Role> roles = roleRepo.findAll();
        return roles;
    }

    public void add(String name){

        Role role = new Role();
        role.setName(name); 
        roleRepo.save(role);

    }

    public void edit(Integer id, String name){
        Role role = new Role();
        role = roleRepo.findById(id).orElseThrow(()-> new RuntimeException("Role isn't found "));
        role.setName(name);
        roleRepo.save(role);
    }

    public void delete(Integer id){
        Role role = roleRepo.findById(id).orElseThrow(() -> new RuntimeException("Role isn't Found"));
        roleRepo.delete(role);
    }

    public Role findById(Integer id){
        Role role = roleRepo.findById(id).orElseThrow(() -> new RuntimeException() );
        return role;
    }

    
}
