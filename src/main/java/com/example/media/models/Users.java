package com.example.media.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "userId")
    private Long id;

    private String name;
    private String email;
    private String password;
    

    @OneToMany
    private List<Posts> userPosts;

}
