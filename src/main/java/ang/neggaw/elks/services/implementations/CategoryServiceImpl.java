package ang.neggaw.elks.services.implementations;

import ang.neggaw.elks.entities.Category;
import ang.neggaw.elks.repositories.CategoryElasticRepository;
import ang.neggaw.elks.services.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * author by: ANG
 * since: 29/08/2021 12:13
 */

@Log4j2
@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryElasticRepository categoryElasticRepository;

    @Override
    public Category createCategory(Category c) {
        return categoryElasticRepository.save(c);
    }

    @Override
    public Category getCategory(String idCat) {
        return categoryElasticRepository.findByIdCategory(idCat);
    }

    @Override
    public Collection<Category> allCategories() {
        Collection<Category> categories = new ArrayList<>();
        categoryElasticRepository.findAll().iterator().forEachRemaining(categories::add);
        return categories;
    }

    @Override
    public Object updateCategory(String idCat, Category c) {

        Category categoryDB = getCategory(idCat);
        if(categoryDB == null) return String.format("Unable to update. Category with id: '%s' Not Found", idCat);
        c.setProducts(categoryDB.getProducts());
        c.setIdCategory(categoryDB.getIdCategory());
        return categoryElasticRepository.save(c);
    }

    @Override
    public Object deleteCategory(String idCat) {
        Category categoryDB = getCategory(idCat);
        if(categoryDB == null) return String.format("Unable to delete. Category with id: '%s' Not Found", idCat);
        categoryElasticRepository.delete(categoryDB);
        if(categoryElasticRepository.findById(idCat).isPresent())
            return String.format("Unable to delete Category with id: '%s'", idCat);
        return String.format("Category with id: '%s' DELETED successfully", idCat);
    }
}
