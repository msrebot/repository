package com.croz.demo.model.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Data
@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_generator")
	@SequenceGenerator(name="category_generator", sequenceName = "category_seq", allocationSize=1)
	private Integer id;
	private String name;
	@OneToMany(mappedBy="category")
	private List<Joke> jokes;
	
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}
	
	
}
