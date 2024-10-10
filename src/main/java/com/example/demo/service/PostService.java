package com.example.demo.service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security..userdetails.User;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.PostNotFoundException;
import com.example.demo.dto.PostDto;
import com.example.demo.mapper.MapperAuth;
import com.example.demo.model.Post;

@Service
public class PostService {

	@Autowired
	private MapperAuth mapper;

	@Autowired
	private AuthService authService;

	public void createPost(PostDto postDto) {
		mapper.createPost(mapFromDtoToPost(postDto));
	}

	public List<PostDto> showAllPosts() {
		List<Post> posts = mapper.showAllPosts();
		return posts.stream().map(this::mapFromPostToDto).collect(Collectors.toList());
	}

	public PostDto readSinglePost(Long id) {
		return mapFromPostToDto(mapper.readSinglePost(id).orElseThrow(() -> new PostNotFoundException("For id " + id)));
	}

	private Post mapFromDtoToPost(PostDto postDto) {
		Post post = new Post();
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		User username = authService.getCurrentUser()
				.orElseThrow(() -> new IllegalArgumentException("No User Logged In"));
		post.setUsername(username.getUsername());
		post.setCreatedOn(Instant.now());
		return post;
	}

	private PostDto mapFromPostToDto(Post post) {
		PostDto postDto = new PostDto();
		postDto.setId(post.getId());
		postDto.setTitle(post.getTitle());
		postDto.setContent(post.getContent());
		postDto.setUsername(post.getUsername());
		return postDto;
	}

}
