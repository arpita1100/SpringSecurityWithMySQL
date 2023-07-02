package com.app.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class MySecurityConfig {

//    @Bean
//    public UserDetailsService userDetailsService()
//    {
//        UserDetails user1= User.builder().username("arpita").password(passwordEncoder().encode("arpita")).roles("ADMIN").build();
//        UserDetails user2=User.builder().username("tanya").password(passwordEncoder().encode("tanya")).build();
////        JdbcUserDetailsManager
//        return new InMemoryUserDetailsManager(user1,user2);
//    }
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager( AuthenticationConfiguration authenticationConfiguration)
    {
        try
        {
            return authenticationConfiguration.getAuthenticationManager();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

}
