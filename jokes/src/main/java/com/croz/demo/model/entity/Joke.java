package com.croz.demo.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Data
@Entity
public class Joke {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "joke_generator")
	@SequenceGenerator(name="joke_generator", sequenceName = "joke_seq", allocationSize=1)
	private Integer id;
	private String content;
	private int likes = 0;
	private int dislikes = 0;
	@ManyToOne
	@JoinColumn(name="category_id", nullable=false)
	private Category category;
	
}
