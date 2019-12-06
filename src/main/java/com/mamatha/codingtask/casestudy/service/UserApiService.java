package com.mamatha.codingtask.casestudy.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.mamatha.codingtask.casestudy.dto.Post;
import com.mamatha.codingtask.casestudy.dto.User;

@Service
public class UserApiService {
	
	@Value("${JsonUserService.baseUrl}")
    private String uri;
	
	@Async
	public Future<Optional<User>> getUser(int userId) {
		 final String userUri = uri+"/users/" + userId;
		RestTemplate restTemplate = new RestTemplate();

		User user = restTemplate.getForObject(userUri, User.class);
		Optional<User> result = Optional.ofNullable(user);
		return new AsyncResult<>(result);
	}

	@Async
	public Future<List<Post>> getPostsByUserId(int userId) throws IOException {
		final String postUri = uri+"/posts?userId="+userId;
		List<Post> posts = new ArrayList<Post>();
		RestTemplate restTemplate = new RestTemplate();

		try {
			ResponseEntity<List<Post>> postsResponse = restTemplate.exchange(postUri, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<Post>>() {
					});
			if (postsResponse != null && postsResponse.hasBody()) {
				posts = postsResponse.getBody();
			}
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		return new AsyncResult<>(posts);

	}
}
