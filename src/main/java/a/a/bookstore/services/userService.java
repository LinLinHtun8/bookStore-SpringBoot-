package a.a.bookstore.services;
import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.Set;

import javax.management.RuntimeErrorException;

import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import a.a.bookstore.configs.myUserDetails;
import a.a.bookstore.models.Role;
import a.a.bookstore.models.appUser;
import a.a.bookstore.repos.roleRepository;
import a.a.bookstore.repos.userRepo;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class userService implements UserDetailsService {

    private final userRepo userRepo;
    private  final roleRepository roleRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<appUser> user = userRepo.findByName(username);

        if(user.isPresent()){

            return new myUserDetails(user.get());

        }
        else throw new UsernameNotFoundException("User Can't find");
    }

    public void add(@NonNull appUser user){
        userRepo.save(user);
    }

    public appUser findByUserID(@NonNull Integer id){
        Optional<appUser> user = userRepo.findById(id);
        return  user.get();
        
    }

    public List<appUser> getAllUsers(){
        List<appUser> users = userRepo.findAll();
        return users;
    } 

    public void addRoleToUser(@NotNull  Integer roleId, @NotNull  Integer userId){
        Optional<Role> role = roleRepo.findById(userId);
        
        if(role.isPresent()){

            appUser user = findByUserID(userId);
            Set<Role> roles = user.getRoles();
            roles.add(role.get());

        }else{
            throw new RuntimeException( "User Not Found");
        }
    }

    public void removeRoleFromUser(@NotNull  Integer roleId, @NotNull  Integer userId){
        Optional<Role> role = roleRepo.findById(userId);
        
        if(role.isPresent()){

            appUser user = findByUserID(userId);
            Set<Role> roles = user.getRoles();
            roles.remove(role.get());

        }else{
            throw new RuntimeException( "User Not Found");
        }
    }

    public void saveUser(appUser user){
        userRepo.save(user);
    }





    
}
