package com.hqm.redis.repositories;

import com.hqm.redis.entities.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, String> {
}
