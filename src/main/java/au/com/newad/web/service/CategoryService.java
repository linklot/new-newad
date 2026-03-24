package au.com.newad.web.service;

import java.util.List;

import au.com.newad.web.model.entity.Category;
import au.com.newad.web.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<Category> getLevelOneCategories() {
        final List<Long> levelOneCategoryIds = List.of(3L, 4L);
        List<Category> categories = categoryRepository.getCategoriesByIds(levelOneCategoryIds);

        categories.forEach(c -> c.getSubCategories());

        return categories;
    }

    @Transactional(readOnly = true)
    public Category getCategoryById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if (category != null) {
            category.getSubCategories();
        }

        return category;
    }

    @Transactional(readOnly = true)
    public Category getCategoryByShortName(String shortName) {
        return categoryRepository.getCategoryByShortName(shortName);
    }
}
