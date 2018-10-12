package com.croz.demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.croz.demo.model.dao.CategoryDao;
import com.croz.demo.model.dao.JokeDao;
import com.croz.demo.service.JokeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JokesApplicationTests {

	@Autowired
	private JokeService jokeService;
	
	@Mock
	private CategoryDao categoryDao;
	
	@Mock
	private JokeDao jokeDao;
	
	@Before
	public void init() {
		categoryDao = Mockito.mock(CategoryDao.class);
		jokeDao = Mockito.mock(JokeDao.class);
		
		jokeService.setCategoryDao(categoryDao);
		jokeService.setJokeDao(jokeDao);
	}
	
	@Test
	public void contextLoads() {
		System.out.println("smoke test: " + jokeService);
	}

}
