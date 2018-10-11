package com.croz.demo.model.dao;

import java.util.List;

import com.croz.demo.model.entity.Category;

public interface CategoryDao {
	Category getCategory(String name);
	List<Category> getCategories();
}
