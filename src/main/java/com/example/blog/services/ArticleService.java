package com.example.blog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.model.Article;
import com.example.blog.repository.ArticleRepository;

@Service
public class ArticleService {

	@Autowired
	private ArticleRepository articleRepo;
	
	public void createArticle(Article article) {
		
		articleRepo.save(article);
	}
	
	public List<Article> findAll() {
	    return articleRepo.findAll();
	}
}
