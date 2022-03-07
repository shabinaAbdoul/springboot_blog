package com.example.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.blog.model.Article;
import com.example.blog.model.User;
import com.example.blog.model.UserLogin;
import com.example.blog.repository.UserRepository;
import com.example.blog.services.ArticleService;

@Controller
public class ArticleController {

	@Autowired
	private ArticleService articleService;
	
	@Autowired UserRepository userRepository;
	
	@GetMapping("/ajout-article")
	public String ajout(Model model) {
				
		return "article/form";
	}
	
	@PostMapping("/ajout-article")
	public String ajout(@Validated Article article, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "article/form";
		}
		
		//On recupere le nom de l'utilisateur connecté
				String username = ((UserLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
				
				if(username != null) {
					//Instnacie un nouvel objet avec le mail de user connecté
					User user = new User();
					user = userRepository.findByEmail(username);
					
					System.out.println(user);
					
					article.setUser(user);
					
					articleService.createArticle(article);
					
					return "article/list";
				}
				
				return "article/ajout";
		
	}
	
	//Liste des articles
	
	@GetMapping("list")
	public String list(Model model) {
		
		List<Article> articles = articleService.findAll();

        model.addAttribute("articles", articles);
       // model.addAttribute("getName", userServices.getCurrentUsername());

        return "article/list";
		
	}
	
	//edit articles
	
		@GetMapping("show/{id}")
		public String show(@PathVariable(value="id") Long articleId, Model model) {

	        model.addAttribute("article", articleService.findById(articleId).get());
	       // model.addAttribute("getName", userServices.getCurrentUsername());

	        return "article/show";
			
		}
}
