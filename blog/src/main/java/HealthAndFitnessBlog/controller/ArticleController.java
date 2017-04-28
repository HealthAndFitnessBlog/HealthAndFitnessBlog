package HealthAndFitnessBlog.controller;


import HealthAndFitnessBlog.bindingModel.ArticleBindingModel;
import HealthAndFitnessBlog.entity.Article;
import HealthAndFitnessBlog.entity.Category;
import HealthAndFitnessBlog.entity.User;
import HealthAndFitnessBlog.repository.ArticleRepository;
import HealthAndFitnessBlog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ArticleController {

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    UserRepository userRepository;


    @GetMapping("/article/create/{category}")
    @PreAuthorize("isAuthenticated()")
    public String create(Model model,@PathVariable("category") String category) {
        model.addAttribute("view", "article/create");

        int categoryId = Category.valueOf(category.toUpperCase()).ordinal();
        model.addAttribute("categoryId", categoryId);


        return "base-layout";
    }

    @PostMapping("/article/create/{category}")
    @PreAuthorize("isAuthenticated()")
    public String createProcess(ArticleBindingModel articleBindingModel) {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        User userEntity = this.userRepository.findByEmail(user.getUsername());

        Category category = Category.values()[articleBindingModel.getCategoryId()];

        Article article = new Article(
                articleBindingModel.getTitle(),
                articleBindingModel.getContent(),
                userEntity,
                category
        );

        this.articleRepository.saveAndFlush(article);

        return "redirect:/";
    }

    @GetMapping("/article/{id}")
    public String details(Model model, @PathVariable Integer id) {
        if (!this.articleRepository.exists(id)) {
            return "redirect:/";
        }

        if(!(SecurityContextHolder.getContext().getAuthentication()
                instanceof AnonymousAuthenticationToken)){

            UserDetails user = (UserDetails) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();

            User userEntity = this.userRepository.findByEmail(user.getUsername());

            model.addAttribute("user",userEntity);
        }

        Article article = this.articleRepository.findOne(id);

        model.addAttribute("article", article);
        model.addAttribute("view", "article/details");

        return "base-layout";
    }

    @GetMapping("/article/edit/{id}")
    @PreAuthorize("isAuthenticated()")
    public String edit(Model model , @PathVariable Integer id) {

        if(!this.articleRepository.exists(id)){
            return "redirect:/";
        }

        Article article = this.articleRepository.findOne(id);

        if(!this.isAuthorOrAdmin(article)){
            return "redirect:/";
        }

        model.addAttribute("article",article);
        model.addAttribute("view", "article/edit");

        return "base-layout";

    }

    @PostMapping("/article/edit/{id}")
    @PreAuthorize("isAuthenticated()")
    public String editProcess(@PathVariable Integer id , ArticleBindingModel model){

        if(!this.articleRepository.exists(id)){

            return "redirect:/";
        }

        Article article = this.articleRepository.findOne(id);

        if(!this.isAuthorOrAdmin(article)){
            return "redirect:/";
        }

        article.setTitle(model.getTitle());
        article.setContent(model.getContent());

        this.articleRepository.saveAndFlush(article);

        return "redirect:/article/" + article.getId();
    }

    @GetMapping("/article/delete/{id}")
    @PreAuthorize("isAuthenticated()")
    public String delete(@PathVariable Integer id, Model model){

        if(!this.articleRepository.exists(id)){

            return "redirect:/";
        }

        Article article = this.articleRepository.findOne(id);

        if(!this.isAuthorOrAdmin(article)){
            return "redirect:/";
        }

        model.addAttribute("article", article);
        model.addAttribute("view", "article/delete");

        return "base-layout";
    }

    @PostMapping("article/delete/{id}")
    @PreAuthorize("isAuthenticated()")
    public String deleteProcess(@PathVariable Integer id ){

        if(!this.articleRepository.exists(id)){
            return "redirect:/";
        }

        Article article = this.articleRepository.findOne(id);

        if(!this.isAuthorOrAdmin(article)){
            return "redirect:/";
        }

        this.articleRepository.delete(id);

        return "redirect:/";
    }

    private boolean isAuthorOrAdmin(Article article){

        UserDetails user = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        User userEntity = this.userRepository.findByEmail(user.getUsername());

        return userEntity.isAdmin() || userEntity.isAuthor(article);
    }





}
