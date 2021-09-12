package com.example.media.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class PostsLikes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne
    private Posts posts;
    @ManyToOne
    private Users users;

}
