package com.example.blogApplicationOnline.service;

import com.example.blogApplicationOnline.model.Category;
import com.example.blogApplicationOnline.model.Post;
import com.example.blogApplicationOnline.model.Tag;
import com.example.blogApplicationOnline.repository.CategoryRepository;
import com.example.blogApplicationOnline.repository.PostRepository;
import com.example.blogApplicationOnline.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private CategoryRepository categoryRepository;



    public List<Post> getPostsByTag(String tagName) {
        Tag tag = tagRepository.findByName(tagName);
        return tag != null ? tag.getPosts() : List.of();
    }

    public List<Post> getPostsByCategory(String categoryName) {
        Category category = categoryRepository.findByName(categoryName);
        return category != null ? category.getPosts() : List.of();
    }
    public List<Post> searchPosts(String keyword) {
        return postRepository.findByTitleContainingOrContentContaining(keyword, keyword);
    }

    public List<Post> searchPostsByTag(String keyword) {

        return postRepository.findByTagKeyword(keyword);
    }

    public List<Post> searchPostsByCategory(String keyword) {

        return postRepository.findByCategoryKeyword(keyword);
    }

    // Add other methods as needed
}
