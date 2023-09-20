package com.example.jwt.jwt.Service;


import com.example.jwt.jwt.Entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private List<User> users= new ArrayList<>();
    public UserService()
    {
        users.add(new User("id1","vinit","rajvinit387@gmail.com"));
        users.add(new User("id2","aditi","aditi@gmail.com"));
      //  users.add(new User("id3","soumya","somu@gmail.com"));
    }

    public List<User> getUsers()
    {
        return users;
    }
}
