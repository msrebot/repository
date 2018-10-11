package com.croz.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.croz.demo.Log;
import com.croz.demo.model.dao.CategoryDao;
import com.croz.demo.model.dao.JokeDao;
import com.croz.demo.model.entity.Category;
import com.croz.demo.model.entity.Joke;
import com.croz.demo.view.JokeForm;

/**
 * Component used to execute business logic for all specified
 * use cases.
 */
@Component
public class JokeService {
	
	@Autowired
	private JokeDao jokeDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	/**
	 * Used to fetch all Joke entities from the data storage.
	 */
	@Log(level = Level.DEBUG)
	public List<Joke> getJokes() {
		return jokeDao.getJokes();
	}
	
	/**
	 * Used to fetch Joke entities from the data storage, with
	 * option to page results.
	 */
	@Log(level = Level.DEBUG)
	public Page<Joke> getJokes(Pageable pageable) {
		return jokeDao.getJokes(pageable);
	}
	
	/**
	 * Used to add a new Joke entity to the storage. It is mapped
	 * from the entity received from view to the domain entity.
	 */
	@Log(level = Level.DEBUG)
	public void addJoke(JokeForm jokeForm) {
		Category category = categoryDao.getCategory(jokeForm.getCategory());
		
		Joke joke = new Joke();
		joke.setCategory(category);
		joke.setContent(jokeForm.getContent());
		
		jokeDao.addJoke(joke);
	}
	
	/**
	 * Used to update a Joke record in the data storage by increasing
	 * the likes counter when joke is liked.
	 */
	@Log(level = Level.DEBUG)
	public void likeJoke(Integer jokeId) {
		Joke joke = jokeDao.getJoke(jokeId);
		joke.setLikes(joke.getLikes() + 1);
		
		jokeDao.addJoke(joke);
	}
	
	/**
	 * Used to update a Joke record in the data storage by increasing
	 * the dislikes counter when joke is disliked.
	 */
	@Log(level = Level.DEBUG)
	public void dislikeJoke(Integer jokeId) {
		Joke joke = jokeDao.getJoke(jokeId);
		joke.setDislikes(joke.getDislikes() + 1);
		
		jokeDao.addJoke(joke);
	}
	
	/**
	 * Used to fetch category names configured in the data
	 * storage, to be able to show them in the form when adding
	 * a new joke.
	 */
	@Log(level = Level.DEBUG)
	public List<String> getCategoryNames() {
		List<Category> categories = categoryDao.getCategories();
		return categories.stream().map(category -> category.getName()).collect(Collectors.toList());
	}
	
	/**
	 * Used to fetch all categories from data storage.
	 */
	@Log(level = Level.DEBUG)
	public List<Category> getCategories() {
		return categoryDao.getCategories();
	}

	public void setJokeDao(JokeDao jokeDao) {
		this.jokeDao = jokeDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	
	
}
