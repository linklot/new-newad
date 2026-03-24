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
    public List<Article> findTop4ArticlesForSlidesView() {
        final long categoryId = 21L;

        return articleRepository.findTop4ByCategoryIdOrderByTimePublishedDesc(categoryId);
    }

    @Transactional(readOnly = true)
    public List<Article> findTop5ArticlesForCategoryView(final Long categoryId) {
        return articleRepository.findTop5TopLevelCategoryId(categoryId);
    }
}
