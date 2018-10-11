package com.croz.demo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class JokeServiceTest {

	@Autowired
	private JokeService jokeService;
	
	@Test
	public void test() {
		System.out.println("smoke test: " + jokeService);
	}
}
