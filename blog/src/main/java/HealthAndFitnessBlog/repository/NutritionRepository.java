package HealthAndFitnessBlog.repository;


import HealthAndFitnessBlog.entity.Article;
import HealthAndFitnessBlog.entity.Nutrition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NutritionRepository  extends JpaRepository<Nutrition, Integer> {
}
