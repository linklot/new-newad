package au.com.newad.web.repository;

import java.util.List;

import au.com.newad.web.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("""
            SELECT c
            FROM Category c
            WHERE c.id IN :ids
            order by c.sequence
            """)
    List<Category> getCategoriesByIds(List<Long> ids);
}
