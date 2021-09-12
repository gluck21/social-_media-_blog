package com.example.media.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class CommentsLikes {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

}
