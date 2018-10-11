package com.croz.demo.model.dao;

import java.util.List;
import java.util.Optional;

import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.croz.demo.Log;
import com.croz.demo.model.entity.Joke;
import com.croz.demo.model.repository.JokeRepository;

@Component
public class JokeDaoImpl implements JokeDao{

	@Autowired
	private JokeRepository jokeRepository;
	
	@Override
	@Log(level = Level.TRACE)
	public List<Joke> getJokes() {
		System.out.println("repo: " + jokeRepository.findAll());
		return (List<Joke>) jokeRepository.findAll();
	}

	@Override
	@Log(level = Level.TRACE)
	public void addJoke(Joke joke) {
		jokeRepository.save(joke);
		
	}

	@Override
	@Log(level = Level.TRACE)
	public Joke getJoke(Integer id) {
		Optional<Joke> joke = jokeRepository.findById(id);
		if (joke.isPresent())
			return joke.get();
		else
			return null;
	}

	@Override
	@Log(level = Level.TRACE)
	public Page<Joke> getJokes(Pageable pageable) {
		return jokeRepository.findAll(pageable);
	}

	
	
}
