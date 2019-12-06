package com.mamatha.codingtask.casestudy.dto;

import java.util.List;
import lombok.Data;

@Data
public class Comments {

	List<String> comments;

	public List<String> getComments() {
		return comments;
	}

	public void setComments(List<String> comments) {
		this.comments = comments;
	}

}
