package au.com.newad.web.controller;

import java.util.List;

import au.com.newad.web.model.entity.Article;
import au.com.newad.web.model.entity.Category;
import au.com.newad.web.service.ArticleService;
import au.com.newad.web.service.CategoryService;
import au.com.newad.web.utils.ModelPropUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class StudyController {

    private static final Logger log = LoggerFactory.getLogger(StudyController.class);

    private final CategoryService categoryService;
    private final ArticleService articleService;

    @GetMapping("/study")
    public String index(Model model) {
        Category cate = categoryService.getCategoryById(3L);
        model.addAttribute("category", cate);

        List<Category> levelOneCategories = categoryService.getLevelOneCategories();
        model.addAttribute("levelOneCategories", levelOneCategories);

        List<Article> studyArticles = articleService.getTop8ByLevelOneCategoryId(3L);
        model.addAttribute("studyArticles", studyArticles);

        model.addAttribute("currentYear", ModelPropUtils.getCurrentYearAsString());

        return "study/index";
    }

    @GetMapping("/study/{shortName}")
    public String category(Model model, @PathVariable final String shortName) {
        List<Category> levelOneCategories = categoryService.getLevelOneCategories();
        model.addAttribute("levelOneCategories", levelOneCategories);

        Category currentCategory = categoryService.getCategoryByShortName(shortName);
        model.addAttribute("currentCategory", currentCategory);

        Category topCategory = currentCategory.getParentCategory();
        model.addAttribute("topCategory", topCategory);

        List<Article> articles = articleService.getArticlesByCategoryId(currentCategory.getId());
        model.addAttribute("articles", articles);

        model.addAttribute("currentYear", ModelPropUtils.getCurrentYearAsString());

        return "study/category";
    }
}
