package com.mamatha.codingtask.casestudy.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mamatha.codingtask.casestudy.dto.Post;
import com.mamatha.codingtask.casestudy.dto.User;
import com.mamatha.codingtask.casestudy.exception.UserNotFoundException;
import com.mamatha.codingtask.casestudy.service.UserApiService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "UserController")
@RestController
public class UserController {

	@Autowired(required = true)
	private UserApiService jsonUserService;

	@ApiOperation(value = "Get User information from Json Call with corresponding posts ", response = Iterable.class, tags = "get")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@GetMapping(value = "/users/{userId}")
	public User get(@PathVariable("userId") int userId)
			throws IOException, InterruptedException, ExecutionException, UserNotFoundException {

		Future<Optional<User>> futureUser = jsonUserService.getUser(userId);
		Future<List<Post>> futurePosts = jsonUserService.getPostsByUserId(userId);

		Optional<User> optionalUser = futureUser.get();
		if (!optionalUser.isPresent()) {
			throw new UserNotFoundException("User not found");
		}
		List<Post> posts = futurePosts.get();
		User user = optionalUser.get();
		user.setPosts(posts);
		return user;
	}

}
