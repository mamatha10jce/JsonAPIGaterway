package com.mamatha.codingtask.casestudy.controller;

import static org.junit.Assert.*;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import com.mamatha.codingtask.casestudy.TestFuture;
import com.mamatha.codingtask.casestudy.dto.Post;
import com.mamatha.codingtask.casestudy.dto.User;
import com.mamatha.codingtask.casestudy.exception.UserNotFoundException;
import com.mamatha.codingtask.casestudy.service.UserApiService;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

public class UserControllerTest {

	private static final int USER_ID = 11;

	private UserController controller;

	private UserApiService userService;

	@Before
	public void before() {
		userService = mock(UserApiService.class);
		controller = new UserController();
	}

	@Test
	public void testGet() throws Exception {

		User user = createUser(USER_ID, "Mamatha Shivanna", "mamatha.shivanna@gmail.com");
		Future<Optional<User>> futureUser = new TestFuture<>(Optional.of(user));
		when(userService.getUser(USER_ID)).thenReturn(futureUser);

		List<Post> posts = new ArrayList<>();
		posts.add(createPost(USER_ID, 1, "Post title 1"));
		posts.add(createPost(USER_ID, 2, "Post title 2"));
		posts.add(createPost(USER_ID, 3, "Post title 3"));
		Future<List<Post>> futurePosts = new TestFuture<>(posts);
		when(userService.getPostsByUserId(USER_ID)).thenReturn(futurePosts);

		User result = controller.get(USER_ID);

		assertThat(result.getId(), is(11));
		assertThat(result.getName(), is("Mamatha Shivanna"));
		assertThat(result.getEmail(), is("mamatha.shivanna@gmail.com"));

	}

	@Test
	public void testGetButUserHasNoPosts() throws Exception {

		User user = createUser(USER_ID, "Mamatha Shivanna", "mamatha.shivanna@gmail.com");
		Future<Optional<User>> futureUser = new TestFuture<>(Optional.of(user));
		when(userService.getUser(USER_ID)).thenReturn(futureUser);

		List<Post> posts = Collections.emptyList();
		Future<List<Post>> futurePosts = new TestFuture<>(posts);
		when(userService.getPostsByUserId(USER_ID)).thenReturn(futurePosts);

		User result = controller.get(USER_ID);

		assertThat(result.getId(), is(1));
		assertThat(result.getName(), is("Max Mustermann"));
		assertThat(result.getEmail(), is("max@mustermann.de"));
		assertThat(result.getPosts(), is(empty()));
	}

	@Test(expected = UserNotFoundException.class)
	public void testGetButUserNotFound() throws Exception {

		Future<Optional<User>> futureUser = new TestFuture<>(Optional.empty());
		when(userService.getUser(USER_ID)).thenReturn(futureUser);

		controller.get(USER_ID);
	}

	private User createUser(int userId, String name, String email) {
		User user = new User();
		user.setId(userId);
		user.setName(name);
		user.setEmail(email);
		return user;
	}

	private Post createPost(int userId, int id, String title) {
		Post post = new Post();
		post.setUserId(userId);
		post.setId(id);
		post.setTitle(title);
		return post;
	}

}
