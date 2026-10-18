package a.a.bookstore.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import a.a.bookstore.services.userService;
import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class webSecurityConfig {

    private final userService userService;

    @Bean
    public UserDetailsService getUserDetailsService(){
        return userService;
    }

    @Bean 
    public BCryptPasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean 
    public AuthenticationProvider getAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userService);
        provider.setPasswordEncoder(getPasswordEncoder());
        return provider;
    }

    @Bean 
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        return http
        
        .csrf(AbstractHttpConfigurer :: disable)
        .formLogin(fl ->{
            fl.loginPage("/users/login").permitAll();
            fl.defaultSuccessUrl("/", true);
        })
        .authorizeHttpRequests(register  ->{
            register.requestMatchers("/users/login", "/users/register", "/css/**", "/js/**", "/imgs/**").permitAll();
            register.anyRequest().authenticated();
        })
        
        .build();

    }
    
}
