package com.mamatha.codingtask.casestudy.dto;

import java.util.ArrayList;
import java.util.List;

public class PostList {
	private List<Post> posts;
	 
    public PostList() {
        posts = new ArrayList<>();
    }

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
    
}
