package com.croz.demo.model.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.croz.demo.model.entity.Joke;

@Repository
@Transactional
public interface JokeRepository extends PagingAndSortingRepository<Joke, Integer> {
	
	/**
	 * Used to fetch all records from the data base using both
	 * paging and sorting. Records returned by method are first
	 * ordered by likes and dislikes difference, and then 
	 * returned in pages.
 	 */
	@Query("select j from Joke j order by j.likes - j.dislikes desc")
	Page<Joke> findAll(Pageable pageable);
}
