package com.example.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog.model.Commentaire;

public interface CommentaireRepository extends JpaRepository<Commentaire, Long> {

}
