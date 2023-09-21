package com.example.jwt.jwt.Controller;

import com.example.jwt.jwt.Security.*;
import com.example.jwt.jwt.Entity.JwtRequest;
import com.example.jwt.jwt.Entity.JwtResponse;
import com.example.jwt.jwt.Security.JwtHelper;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class JwtController  {

  // public   static int flag=0;





    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;


    @Autowired
    private JwtHelper helper;

    private Logger logger = LoggerFactory.getLogger(JwtController.class);



    @PostMapping ("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {


      // flag=1;
       //System.out.println(flag);
        if(request==null || request.getEmail().equals("")|| request.getPassword().equals(""))
        {
            throw new NullPointerException("Body is empty");
        }
       this.doAuthenticate(request.getEmail(),  request.getPassword());
       UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }



    @ExceptionHandler(NullPointerException.class)
    public String exceptionHandler1() {
        return "Something is missing in credential !!";
    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler2() {
        return "Credentials Invalid !!";
    }
}
