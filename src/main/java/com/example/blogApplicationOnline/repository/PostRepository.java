package com.example.blogApplicationOnline.repository;

import com.example.blogApplicationOnline.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUserProfileId(Long userProfileId);

    List<Post> findByTitleContainingOrContentContaining(String title, String content);


    @Query("SELECT DISTINCT p FROM Post p WHERE p.title LIKE %:keyword% OR p.content LIKE %:keyword%")
    List<Post> findByTitleContainingOrContentContaining(@Param("keyword") String keyword);

    @Query("SELECT DISTINCT p FROM Post p JOIN p.tags t WHERE t.name LIKE %:keyword%")
    List<Post> findByTagKeyword(String keyword);

    @Query("SELECT DISTINCT p FROM Post p JOIN p.categories c WHERE c.name LIKE %:keyword%")
    List<Post> findByCategoryKeyword(String keyword);
}
