package com.croz.demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.croz.demo.model.dao.JokeDao;
import com.croz.demo.model.entity.Joke;
import com.croz.demo.service.JokeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JokesApplicationTests {

	@Autowired
	private JokeService jokeService;
	
	@Mock
	private JokeDao jokeDao;
	
	@Before
	public void init() {
		jokeDao = Mockito.mock(JokeDao.class);
		
		jokeService.setJokeDao(jokeDao);
	}
	/*
	 * To test if liking the joke works well. Since method to like the joke
	 * is void, a good way to do the test is to check whether the component
	 * that stores the updated entity in data base, receives the correct value
	 * as parameter.
	 * */
	@Test
	public void incrementLikesTest() {
		Joke joke1 = new Joke();
		
		Joke joke2 = new Joke();
		joke2.setLikes(1);
		
		// joke1 will be returned if joke with id 1 is to be liked
		Mockito.doReturn(joke1).when(jokeDao).getJoke(1);
		
		jokeService.likeJoke(1);
		
		// addJoke method, which should be invoked at the end of the
		// use case execution, should receive joke entity, with number of 
		// likes incremented by 1. joke2 is created to simulate that.
		Mockito.verify(jokeDao).addJoke(joke2);
		
	}
}
