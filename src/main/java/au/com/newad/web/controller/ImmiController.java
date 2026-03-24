package au.com.newad.web.controller;

import java.util.List;

import au.com.newad.web.model.entity.Article;
import au.com.newad.web.model.entity.Category;
import au.com.newad.web.service.ArticleService;
import au.com.newad.web.service.CategoryService;
import au.com.newad.web.utils.ModelPropUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class ImmiController {

    private final CategoryService categoryService;
    private final ArticleService articleService;

    @GetMapping("/immi")
    public String index(Model model) {
        Category cate = categoryService.getCategoryById(4L);
        model.addAttribute("category", cate);

        List<Category> levelOneCategories = categoryService.getLevelOneCategories();
        model.addAttribute("levelOneCategories", levelOneCategories);

        List<Article> immiArticles = articleService.getTop8ByLevelOneCategoryId(4L);
        model.addAttribute("immiArticles", immiArticles);

        model.addAttribute("currentYear", ModelPropUtils.getCurrentYearAsString());

        return "immi/index";
    }

    @GetMapping("/immi/{shortName}")
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

        return "immi/category";
    }

    @GetMapping("/immi/{categoryShortName}/{articleId}")
    public String article(Model model,
            @PathVariable final String categoryShortName,
            @PathVariable final Long articleId) {
        Category currentCategory = categoryService.getCategoryByShortName(categoryShortName);
        model.addAttribute("currentCategory", currentCategory);

        Category topCategory = currentCategory.getParentCategory();
        model.addAttribute("topCategory", topCategory);

        List<Category> levelOneCategories = categoryService.getLevelOneCategories();
        model.addAttribute("levelOneCategories", levelOneCategories);

        Article article = articleService.getArticleById(articleId);
        model.addAttribute("article", article);

        List<Article> otherArticles = articleService.getOtherArticlesInSameCategory(currentCategory.getId(), articleId);
        model.addAttribute("relatedArticles", otherArticles);

        model.addAttribute("currentYear", ModelPropUtils.getCurrentYearAsString());

        return "immi/article";
    }
}
