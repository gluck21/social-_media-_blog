package com.example.media.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postId")
    private Long id;

    private String message;
    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comments> comments;
   @OneToMany
    private List<PostsLikes> postsLikes;

}
