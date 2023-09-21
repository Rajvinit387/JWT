package com.example.jwt.jwt.Service;


import com.example.jwt.jwt.Entity.User;
import com.example.jwt.jwt.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;





    @Autowired
    private UserRepository userRepository;


    public UserService()
    {
       // users.add(new User("id1","vinit","rajvinit387@gmail.com"));
        //users.add(new User("id2","aditi","aditi@gmail.com"));
      //  users.add(new User("id3","soumya","somu@gmail.com"));
    }

    public List<User> getUsers()
    {
       List<User> users= this.userRepository.findAll();
       return  users;
    }

    public User addUser(User user)
    {
        String decodedPassword= user.getPassword();
        user.setPassword(this.bCryptPasswordEncoder.encode(decodedPassword));
       User u= this.userRepository.save(user);
          u.setPassword(decodedPassword);
        return u;
    }

}
