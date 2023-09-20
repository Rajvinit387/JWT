package com.example.jwt.jwt.Entity;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtResponse {

    private String username;

    private  String jwtToken;
}
