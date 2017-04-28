package HealthAndFitnessBlog.controller;

import HealthAndFitnessBlog.entity.Article;
import HealthAndFitnessBlog.entity.Category;
import HealthAndFitnessBlog.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/")
    public String index(Model model) {

        List<Article> articles = this.articleRepository.findAll();

        model.addAttribute("view", "home/index");
        model.addAttribute("article", articles);

        return "base-layout";


    }

    @GetMapping("/{category}")
    public String home(Model model, @PathVariable("category") String category) {


        List<Article> articles = this.articleRepository.findAll();
        model.addAttribute("view", "home/category");
        model.addAttribute("article",articles);

        Category categoryId = Category.valueOf(category.toUpperCase());
        List<Article> filteredArticles = new ArrayList<>();

        for (Article article : articles) {
            if (categoryId == article.getCategory()) {
                filteredArticles.add(article);
            }
        }

        model.addAttribute("article", filteredArticles);

        return "base-layout";
    }

//    @GetMapping("/training")
//    public String training(Model model) {
//
//        List<Article> articles = this.articleRepository.findAll();
//        model.addAttribute("view", "home/training");
//        model.addAttribute("article", articles);
//
//        model.addAttribute("category","training");
//        return "base-layout";
//    }
//
//    @GetMapping("/supplements")
//    public String supplements(Model model) {
//
//        List<Article> articles = this.articleRepository.findAll();
//
//        model.addAttribute("view", "home/supplements");
//        model.addAttribute("article", articles );
//
//        model.addAttribute("category","supplements");
//        return "base-layout";
//    }
}
