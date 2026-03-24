package au.com.newad.web.service;

import java.util.List;

import au.com.newad.web.model.entity.Article;
import au.com.newad.web.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleService {

    private final ArticleRepository articleRepository;

    public List<Article> getTop4ArticlesForSlidesView() {
        final long categoryId = 21L;

        return articleRepository.findTop4ByCategoryIdOrderByTimePublishedDesc(categoryId);
    }

    public List<Article> getTop5ArticlesForCategoryView(final Long categoryId) {
        return articleRepository.findTop5TopLevelCategoryId(categoryId);
    }

    public List<Article> getTop8ByLevelOneCategoryId(final Long levelOneCategoryId) {
        return articleRepository.getTop8ByLevelOneCategoryId(levelOneCategoryId);
    }

    public List<Article> getArticlesByCategoryId(final Long categoryId) {
        return articleRepository.getArticlesByCategoryId(categoryId);
    }

    public Article getArticleById(final Long articleId) {
        Article article = articleRepository.findById(articleId).orElse(null);
        if (article != null) {
            String rawContent = article.getContent().getContent()
                    .replace("\\r\\n", "")
                    .replace("\\\"", "'");
            article.getContent().setContent(rawContent);
        }
        return article;
    }

    public List<Article> getOtherArticlesInSameCategory(final Long categoryId, final Long articleId) {
        List<Article> articles = getArticlesByCategoryId(categoryId);

        articles.removeIf(a -> a.getId().equals(articleId));

        return articles;
    }
}
