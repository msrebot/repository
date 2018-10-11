package com.croz.demo.model.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.croz.demo.model.entity.Joke;

public interface JokeDao {
	
	Joke getJoke(Integer id);
	List<Joke> getJokes();
	Page<Joke> getJokes(Pageable pageable);
	void addJoke(Joke joke);

}
