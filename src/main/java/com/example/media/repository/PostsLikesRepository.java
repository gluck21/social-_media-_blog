package com.example.media.repository;

import com.example.media.models.PostsLikes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostsLikesRepository extends JpaRepository<PostsLikes, Long> {
   Optional<PostsLikes> findAllByUsersIdAndPostsId(Long usersId, Long postsId);
}
