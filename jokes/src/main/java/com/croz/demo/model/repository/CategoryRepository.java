package com.croz.demo.model.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.croz.demo.model.entity.Category;

@Repository
@Transactional
public interface CategoryRepository extends CrudRepository<Category, Integer>{
	Category findByName(String name);
}
