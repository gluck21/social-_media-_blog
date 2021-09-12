package com.example.media.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String message;
    @OneToMany
    private List<CommentsLikes> commentsLikes;

}
