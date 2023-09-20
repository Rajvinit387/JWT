package com.example.jwt.jwt.Entity;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class JwtRequest {

    private  String email;

    private  String password;

}
