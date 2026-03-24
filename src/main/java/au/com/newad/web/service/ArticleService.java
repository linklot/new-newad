package au.com.newad.web.service;

import java.util.List;

import au.com.newad.web.model.entity.Article;
import au.com.newad.web.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public List<Article> getTop4ArticlesForSlidesView() {
        final long categoryId = 21L;

        return articleRepository.findTop4ByCategoryIdOrderByTimePublishedDesc(categoryId);
    }

    @Transactional(readOnly = true)
    public List<Article> getTop5ArticlesForCategoryView(final Long categoryId) {
        return articleRepository.findTop5TopLevelCategoryId(categoryId);
    }

    @Transactional(readOnly = true)
    public List<Article> getTop8ByLevelOneCategoryId(final Long levelOneCategoryId) {
        return articleRepository.getTop8ByLevelOneCategoryId(levelOneCategoryId);
    }

    @Transactional(readOnly = true)
    public List<Article> getArticlesByCategoryId(final Long categoryId) {
        return articleRepository.getArticlesByCategoryId(categoryId);
    }
}
