package com.example.jwt.jwt.Config;


import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.ui.Model;

import java.util.Enumeration;

@Configuration
public class myconfig {

    @Bean
    public PasswordEncoder getBrcyptPasswordEncoder()
    {
        return  new BCryptPasswordEncoder();
    }

   /* @Bean
    public UserDetailsService getUserDetailsService()
    {
        UserDetails user= User.withUsername("vinit").password(getBrcyptPasswordEncoder().encode("vinit")).
                build();
        UserDetails user1= User.withUsername("aditi").password(getBrcyptPasswordEncoder().encode("aditi")).
                build();
        return  new InMemoryUserDetailsManager(user,user1);
    } */


    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider daoAuthenticationProvider= new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(this.getuserDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(getBrcyptPasswordEncoder());
        return daoAuthenticationProvider;
    }


    @Bean
    public UserDetailsService getuserDetailsService()
    {
        return  new UserDetailsServiceImpl();
    }







    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }



}
