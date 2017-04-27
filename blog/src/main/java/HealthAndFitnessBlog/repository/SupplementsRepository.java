package HealthAndFitnessBlog.repository;


import HealthAndFitnessBlog.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplementsRepository  extends JpaRepository<Article, Integer> {
}
