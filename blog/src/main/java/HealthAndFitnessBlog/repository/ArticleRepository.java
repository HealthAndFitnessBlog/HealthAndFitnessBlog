package HealthAndFitnessBlog.repository;

import HealthAndFitnessBlog.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ArticleRepository extends JpaRepository<Article, Integer> {
}
