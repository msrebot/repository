package com.croz.demo.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.croz.demo.Log;
import com.croz.demo.model.entity.Joke;
import com.croz.demo.service.JokeService;
import com.croz.demo.view.JokeForm;
import com.croz.demo.view.JokeId;

/**
 * Main entry point to the application. Exposes end points to 
 * fetch, add, like and dislike jokes. Adding new jokes requires
 * authentication. Other use cases don't.
 */
@Controller
public class JokesController {

	@Autowired
	private JokeService jokeService;
	
	/**
	 * End point to fetch all jokes. It is possible to perform the pagination
	 * by specifying size and page path parameters. If they are not specified,
	 * default values of 10 and 0 will be used.
	 * 
	 * End point retrieves the view, displaying details about jokes: category,
	 * content, number of likes and dislikes, and difference between them. Also,
	 * the view contains buttons to like and dislike the joke.
	 */
	@GetMapping("/jokes")
	@Log(level = Level.INFO)
	public String getJokes(@PageableDefault(value = 10, page = 0) Pageable pageable, Model model) {
		Page<Joke> jokes = jokeService.getJokes(pageable);
		model.addAttribute("jokes", jokes);
		model.addAttribute("jokeId", new JokeId());
		
		int totalPages = jokes.getTotalPages();
		if (totalPages > 0){
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
					.boxed()
					.collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		} 
		
		return "jokes";
	}
	
	/**
	 * End point to add a new joke. End point retrieves the view to 
	 * submit category and content of the joke.
	 */
	@GetMapping("/joke")
	@Log(level = Level.INFO)
	public String addJokeForm(Model model) {
		model.addAttribute("categories", jokeService.getCategories());
		model.addAttribute("jokeForm", new JokeForm());
		return "jokeform";
	}
	
	/**
	 * End point invoked when details are submitted in the
	 * add joke form.
	 */
	@PostMapping("/joke")
	@Log(level = Level.INFO)
	public String addJoke(@Valid @ModelAttribute JokeForm jokeForm, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors() == false) 		
			jokeService.addJoke(jokeForm);
		
		return "redirect:/joke";
	}
	
	/**
	 * End point invoked when button is pressed, to 
	 * like the joke.
	 */
	@PostMapping("/inc")
	@Log(level = Level.INFO)
	public String like(@ModelAttribute JokeId jokeId) {
		jokeService.likeJoke(Integer.parseInt(jokeId.getId()));
		return "redirect:/jokes";
	}
	
	/**
	 * End point invoked when button is pressed, to 
	 * dislike the joke.
	 */
	@PostMapping("/dec")
	@Log(level = Level.INFO)
	public String dislike(@ModelAttribute JokeId jokeId) {
		jokeService.dislikeJoke(Integer.parseInt(jokeId.getId()));
		return "redirect:/jokes";
	}
	
	/**
	 * End point invoked when authentication is required.
	 */
	@GetMapping("/login")
	@Log(level = Level.INFO)
	public String login() {
		return "login";
	}
	
}
