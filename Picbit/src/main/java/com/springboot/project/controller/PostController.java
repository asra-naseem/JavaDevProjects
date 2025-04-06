package com.springboot.project.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.project.payloads.PostDto;
import com.springboot.project.payloads.PostResponse;
import com.springboot.project.repositories.PostRepo;
import com.springboot.project.services.FileService;
import com.springboot.project.services.PostService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class PostController {

    private final PostRepo postRepo;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String Imagepath;

    PostController(PostRepo postRepo) {
        this.postRepo = postRepo;
    }
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId){
		return new ResponseEntity<>(postService.createPost(postDto, userId, categoryId),HttpStatus.CREATED);
		
	}
	
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategoryId(@PathVariable Integer categoryId){
		return new ResponseEntity<List<PostDto>>(this.postService.getPostByCategory(categoryId),HttpStatus.OK);
		
	}
	
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(@RequestParam(value="pageNumber",defaultValue="0",required=false) Integer pageNumber,@RequestParam(value="pageSize",defaultValue="2",required=false) Integer pageSize,@RequestParam(value="sortBy",defaultValue="Id",required=false) String sortBy){
		return new ResponseEntity<PostResponse>(this.postService.getAllPost(pageNumber,pageSize,sortBy),HttpStatus.OK);
		
	}
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
		return new ResponseEntity<>(this.postService.getSinglePost(postId),HttpStatus.OK);
		
	}
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUserId(@PathVariable Integer userId){
		return new ResponseEntity<List<PostDto>>(this.postService.getPostByUser(userId),HttpStatus.OK);
		
	}
	
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePostByPostId(@RequestBody PostDto postDto,@PathVariable Integer postId){
		return new ResponseEntity<PostDto>(this.postService.updatePost(postDto,postId),HttpStatus.OK);
		
	}
	
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<?> deletePostByPostId(@PathVariable Integer postId){
		this.postService.deletePost(postId);
		return new ResponseEntity(Map.of("message","User deleted successfully"),HttpStatus.OK);
		
	}
	@GetMapping("/posts/search/{keyword:[a-zA-Z]+}")
	public ResponseEntity<?> getPostByTitle(@PathVariable String keyword){
		return new ResponseEntity<List<PostDto>>(this.postService.getPostByTitle(keyword),HttpStatus.OK);
	}
	
	@PostMapping("/posts/update/image/{postId}")
	public ResponseEntity<?> uploadImage(@PathVariable Integer postId,@RequestParam("image") MultipartFile image ) throws IOException{
		PostDto postDto=this.postService.getSinglePost(postId);
		
		postDto.setImageName(this.fileService.uploadImage(Imagepath, image));
		PostDto updatePostDto= this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePostDto,HttpStatus.OK);
		
		
		}
	
	@GetMapping(value="/post/image/{imageName}",produces=MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(
			@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {
		
		InputStream resource =this.fileService.getSource(Imagepath, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource,response.getOutputStream());
		
		
	}
	
	
	

}
