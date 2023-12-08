package com.example.blogApplicationOnline.controller;

import com.example.blogApplicationOnline.model.Comment;
import com.example.blogApplicationOnline.repository.PostRepository;
import com.example.blogApplicationOnline.service.CommentService;
import com.example.blogApplicationOnline.service.PostService;
import com.example.blogApplicationOnline.service.UserProfileService;
import com.example.blogApplicationOnline.model.Post;
import com.example.blogApplicationOnline.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserProfileService userProfileService;
    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

    @GetMapping("/getpost")
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @PostMapping@GetMapping
    public Post createPost(@RequestBody Post post) {
        return postRepository.save(post);
    }
    @PostMapping("/insertpost")
    public Post insertPost(@RequestBody Post post) {
        // Handle the logic to insert a post
        return postRepository.save(post);
    }

    @GetMapping("/user/{username}")
    public List<Post> getPostsByUser(@PathVariable String username) {
        UserProfile userProfile = userProfileService.getUserProfileByUsername(username);
        return postRepository.findByUserProfileId(userProfile.getId());
    }

    @GetMapping("/{postId}/comments")
    public List<Comment> getCommentsByPostId(@PathVariable Long postId) {
        return commentService.getCommentsByPostId(postId);
    }

    @PostMapping("/{postId}/comments")
    public Comment createComment(@PathVariable Long postId, @RequestBody Comment comment, @RequestParam String username) {
        UserProfile userProfile = userProfileService.getUserProfileByUsername(username);
        comment.setUserProfile(userProfile);

        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        comment.setPost(post);

        return commentService.createComment(comment);
    }

    @GetMapping("/tags/{tagName}")
    public List<Post> getPostsByTag(@PathVariable String tagName) {
        return postService.getPostsByTag(tagName);
    }

    @GetMapping("/categories/{categoryName}")
    public List<Post> getPostsByCategory(@PathVariable String categoryName) {
        return postService.getPostsByCategory(categoryName);
    }
    @GetMapping("/search")
    public List<Post> searchPosts(@RequestParam String keyword) {
        // Trim the keyword to remove leading and trailing whitespaces
        keyword = keyword.trim();
        return postService.searchPosts(keyword);
    }

    @GetMapping("/search/tags")
    public List<Post> searchPostsByTag(@RequestParam String keyword) {
        return postService.searchPostsByTag(keyword);
    }

    @GetMapping("/search/categories")
    public List<Post> searchPostsByCategory(@RequestParam String keyword) {
        return postService.searchPostsByCategory(keyword);
    }
//    @PostMapping
//    public Post createPost(@RequestBody Post post, @RequestParam String username) {
//        UserProfile userProfile = userProfileService.getUserProfileByUsername(username);
//        post.setUserProfile(userProfile);
//        return postRepository.save(post);
//    }



    // Add other CRUD methods as needed
}
