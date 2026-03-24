package au.com.newad.web.controller;

import java.util.List;

import au.com.newad.web.model.entity.Article;
import au.com.newad.web.model.entity.Category;
import au.com.newad.web.service.ArticleService;
import au.com.newad.web.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private static final Logger log = LoggerFactory.getLogger(IndexController.class);
    private final CategoryService categoryService;
    private final ArticleService articleService;

    @GetMapping("/")
    public String index(Model model) {
        List<Article> topArticles = articleService.findTop4ArticlesForSlidesView();
        model.addAttribute("topArticles", topArticles);

        List<Article> studyArticles = articleService.findTop5ArticlesForCategoryView(3L);
        model.addAttribute("studyArticles", studyArticles);

        List<Article> immiArticles = articleService.findTop5ArticlesForCategoryView(4L);
        model.addAttribute("immiArticles", immiArticles);

        List<Category> levelOneCategories = categoryService.getLevelOneCategories();
        model.addAttribute("levelOneCategories", levelOneCategories);

        model.addAttribute("currentYear", getCurrentYear());

        return "index";
    }

    @GetMapping("/about")
    public String about(Model model) {
        List<Category> levelOneCategories = categoryService.getLevelOneCategories();
        model.addAttribute("levelOneCategories", levelOneCategories);

        model.addAttribute("currentYear", getCurrentYear());

        return "about";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        List<Category> levelOneCategories = categoryService.getLevelOneCategories();
        model.addAttribute("levelOneCategories", levelOneCategories);

        model.addAttribute("currentYear", getCurrentYear());

        return "contact";
    }

    private String getCurrentYear() {
        return String.valueOf(java.time.Year.now().getValue());
    }
}
