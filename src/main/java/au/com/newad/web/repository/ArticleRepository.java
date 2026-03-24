package au.com.newad.web.repository;

import java.util.List;

import au.com.newad.web.model.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findTop4ByCategoryIdOrderByTimePublishedDesc(Long categoryId);

    @Query("""
            SELECT a
            FROM Article a
            JOIN a.category c
            WHERE c.parentCategory.id = :parentId
            ORDER BY a.timePublished DESC
            LIMIT 5
            """)
    List<Article> findTop5TopLevelCategoryId(Long parentId);

}
