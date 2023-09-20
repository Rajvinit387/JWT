package com.example.jwt.jwt.Security;
import com.example.jwt.jwt.Controller.*;

import com.example.jwt.jwt.Controller.JwtController;
import com.example.jwt.jwt.Entity.JwtRequest;
import com.example.jwt.jwt.Service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

//import static com.example.jwt.jwt.Controller.JwtController.flag;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {





    @Autowired
    private JwtHelper jwtHelper;


    @Autowired
    private UserDetailsService userDetailsService;

    private Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);

     @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String username = null;
        String token = null;
       // if(this.jwtController.flag==0)
       // {

       //  byte[] inputStreamBytes = StreamUtils.copyToByteArray(request.getInputStream());
       // Map<String, String> jsonRequest = new ObjectMapper().readValue(inputStreamBytes, Map.class);
        // String requestBodyJsonString = jsonRequest.get("Body");
       //System.out.println(requestBodyJsonString);

      // String x= (String) request.getAttribute("email");
       //  System.out.println(x);

              String requestHeader = request.getHeader("Authorization");


              //Bearer 2352345235sdfrsfgsdfsdf
             // logger.info(" Header :  {}", requestHeader);

              if (requestHeader != null && requestHeader.startsWith("Bearer")) {
                  //looking good
                  logger.info(" Header :  {}", requestHeader);
                  token = requestHeader.substring(7);
                  try {

                      username = this.jwtHelper.getUsernameFromToken(token);

                  } catch (IllegalArgumentException e) {
                      logger.info("Illegal Argument while fetching the username !!");
                      e.printStackTrace();
                  } catch (ExpiredJwtException e) {
                      logger.info("Given jwt token is expired !!");
                      e.printStackTrace();
                  } catch (MalformedJwtException e) {
                      logger.info("Some changed has done in token !! Invalid Token");
                      e.printStackTrace();
                  } catch (Exception e) {
                      e.printStackTrace();

                  }


              } else if(requestHeader!=null && !(requestHeader.startsWith("Bearer"))) {
                  logger.info(" Header :  {}", requestHeader);
                  logger.info("Invalid Header Value !! ");
              }





        //}
      //  this.jwtController.flag=1;


        //
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {


            //fetch user detail from username
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            Boolean validateToken = this.jwtHelper.validateToken(token, userDetails);
            if (validateToken) {

                //set the authentication
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);


            } else {
                logger.info("Validation fails !!");
            }


        }

        filterChain.doFilter(request, response);


    }
}





