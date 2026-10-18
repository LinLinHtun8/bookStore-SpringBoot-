package a.a.bookstore.services;

import java.lang.foreign.Linker.Option;
import java.util.Collection;
import java.util.Optional;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import a.a.bookstore.configs.myUserDetails;
import a.a.bookstore.models.appUser;
import a.a.bookstore.repos.userRepo;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class userService implements UserDetailsService {

    private final userRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<appUser> user = userRepo.findByName(username);

        if(user.isPresent()){

            return new myUserDetails(user.get());

        }
        else throw new UsernameNotFoundException("User Can't find");
    }

    public void add(appUser user){
        userRepo.save(user);
    }


    
}
