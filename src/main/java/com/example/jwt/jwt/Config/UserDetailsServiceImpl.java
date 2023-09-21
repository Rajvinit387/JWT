package com.example.jwt.jwt.Config;

import com.example.jwt.jwt.Entity.User;
import com.example.jwt.jwt.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl  implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user= this.userRepository.getUserbyEmail(username);
        if(user==null)
        {
            throw new UsernameNotFoundException("User doesn't exist");
        }

        CustomUserDetails customUserDetails= new CustomUserDetails(user);
        return customUserDetails;
    }
}
