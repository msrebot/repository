package com.croz.demo.view;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class JokeForm {

	@Pattern(regexp = "^[A-Za-z]+$")
	private String content;
	@NotBlank
	private String category;
	
}
