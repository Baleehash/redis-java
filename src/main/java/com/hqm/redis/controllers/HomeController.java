package com.hqm.redis.controllers;

import com.hqm.redis.entities.Article;
import com.hqm.redis.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HomeController {

    @Autowired
    ArticleRepository articleRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> home() {
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/articles", method = RequestMethod.GET)
    public ResponseEntity<List<Article>> getAll(Model model) {
        List<Article> articles = new ArrayList<>();
        articleRepository.findAll().forEach(articles::add);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @RequestMapping(value = "/article/{id}", method = RequestMethod.GET)
    public ResponseEntity<Article> findById(@PathVariable("id") String id) {
        Article article = articleRepository.findById(id).orElse(null);
        if (article == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    @RequestMapping(value = "/article", method = RequestMethod.POST)
    public ResponseEntity<Article> save(@RequestBody Article article) {
        Article art = new Article(article.getTitle(), article.getType(), article.getContent());
        articleRepository.save(art);
        return new ResponseEntity<>(art, HttpStatus.OK);
    }

    @RequestMapping(value = "/article/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Article> updateById(@PathVariable("id") String id, @RequestBody Article updatedArticle) {
        Article existingArticle = articleRepository.findById(id).orElse(null);
        if (existingArticle == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        existingArticle.setTitle(updatedArticle.getTitle());
        existingArticle.setType(updatedArticle.getType());
        existingArticle.setContent(updatedArticle.getContent());

        articleRepository.save(existingArticle);
        return new ResponseEntity<>(existingArticle, HttpStatus.OK);
    }

    @RequestMapping(value = "/article/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        articleRepository.deleteById(id);
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
