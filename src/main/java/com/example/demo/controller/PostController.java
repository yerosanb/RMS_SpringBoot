package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PostDto;
import com.example.demo.service.PostService;

//@CrossOrigin
@RestController
@RequestMapping("/api/posts")
public class PostController {

	@Autowired
	private PostService service;

	@PostMapping("/addpost")
	public ResponseEntity createPost(@RequestBody PostDto postDto) {
		service.createPost(postDto);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<PostDto>> showAllPosts(){
		return new ResponseEntity<>(service.showAllPosts(), HttpStatus.OK);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<PostDto> getSinglePost(@PathVariable @RequestBody Long id){
		return new ResponseEntity<>(service.readSinglePost(id), HttpStatus.OK);
	}
}
