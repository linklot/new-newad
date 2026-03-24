package au.com.newad.web.service;

import java.util.List;

import au.com.newad.web.model.entity.Category;
import au.com.newad.web.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getLevelOneCategories() {
        final List<Long> levelOneCategoryIds = List.of(3L, 4L);
        List<Category> categories = categoryRepository.getCategoriesByIds(levelOneCategoryIds);

        categories.forEach(c -> c.getSubCategories());

        return categories;
    }
}
