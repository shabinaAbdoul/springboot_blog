package com.example.blog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.blog.model.User;
import com.example.blog.repository.UserRepository;


@Service
public class UserServices {

	@Autowired
	private UserRepository userRepo;
	
	
	public User findByEmail(String email) {
        return  userRepo.findByEmail(email);
    }
	
	//Je crée une methode C du CRUD
	
	public void createUser(User user) {
		
		BCryptPasswordEncoder crypt = new BCryptPasswordEncoder();
		user.setPwd(crypt.encode(user.getPwd()));
		
		userRepo.save(user);
	}
}
