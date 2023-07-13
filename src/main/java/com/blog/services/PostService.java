package com.blog.services;

import com.blog.dto.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PostService {
    List<PostDto> findAllPosts();

    void createPost(PostDto postDto);

    PostDto findPostById(Long postId);

    void updatePost(PostDto postDto);

    void deletePost(Long postId);

    void findPostByUrl(Long postId);


    PostDto findPostByUrl(String postUrl);

    List<PostDto> searchPosts(String query);
}
