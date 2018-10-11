package com.croz.demo.model.dao;

import java.util.List;

import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.croz.demo.Log;
import com.croz.demo.model.entity.Category;
import com.croz.demo.model.repository.CategoryRepository;

@Component
public class CategoryDaoImpl implements CategoryDao{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	@Log(level = Level.TRACE)
	public Category getCategory(String name) {
		return categoryRepository.findByName(name);
	}

	@Override
	@Log(level = Level.TRACE)
	public List<Category> getCategories() {
		return (List<Category>) categoryRepository.findAll();
	}

}
