package com.croz.demo.view;

import lombok.Data;

@Data
public class JokeView {
	
	private Integer id;
	private String content;
	private int likes;
	private int dislikes;
	private String category;
	private int likesDiff;
}
