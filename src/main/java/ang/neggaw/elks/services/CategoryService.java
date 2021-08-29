package ang.neggaw.elks.services;

import ang.neggaw.elks.entities.Category;

import java.util.Collection;

/**
 * author by: ANG
 * since: 29/08/2021 12:13
 */

public interface CategoryService {
    Category createCategory(Category c);
    Category getCategory(String idCat);
    Collection<Category> allCategories();
    Object updateCategory(String idCat, Category c);
    Object deleteCategory(String idCat);
}
