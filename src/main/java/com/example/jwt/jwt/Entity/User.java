package com.example.jwt.jwt.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int uid;

    private String name;



    private String email;

    private String password;


}
