package com.example.jwt.jwt.Controller;


import com.example.jwt.jwt.Entity.User;
import com.example.jwt.jwt.Security.JwtAuthenticationFilter;
import com.example.jwt.jwt.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/home")
public class mycontroller {


  //  @Autowired
   // private JwtController jwtController;
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public List<User> getuser()
    {
        //this.jwtController.flag=0;
        System.out.println("getting user..");
     // JwtAuthenticationFilter.flag=1;
        return this.userService.getUsers();

    }
    @GetMapping("/currentUser")
    public String getCurrentUser(Principal principal)
    {
        //this.jwtController.flag=0;
    //  JwtAuthenticationFilter.flag=1;
      return  principal.getName();

    }





}
