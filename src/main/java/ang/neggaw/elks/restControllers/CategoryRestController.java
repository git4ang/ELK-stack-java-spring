package ang.neggaw.elks.restControllers;

import ang.neggaw.elks.entities.Category;
import ang.neggaw.elks.services.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * author by: ANG
 * since: 29/08/2021 12:18
 */

@Log4j2
@RequiredArgsConstructor
@RequestMapping(value = "/api/categories")
@RestController
public class CategoryRestController {

    private final CategoryService categoryService;

    @PostMapping
    public Category createCategory(@RequestBody Category category) {

        log.info("Creating Category with name '{}'...", category.getName());

        Category categoryDB = categoryService.createCategory(category);
        if(categoryDB == null) {
            log.error("Unable to create Category with name: '{}'", category.getName());
            return null;
        }

        log.info("Category with id: '{}' CREATED successfully", categoryDB.getIdCategory());
        return categoryDB;

    }

    @GetMapping(value = "/{idCategory}")
    public Category getCategory(@PathVariable(value = "idCategory") String idCategory) {

        log.info("Fetching Category with id: '{}'...", idCategory);
        Category categoryDB = categoryService.getCategory(idCategory);
        if(categoryDB == null) {
            log.error("Unable to find Category with id: '{}'", idCategory);
            return null;
        }
        return categoryDB;
    }

    @GetMapping
    public Collection<Category> allCategories() {

        log.info("Fetching all categories...");
        return categoryService.allCategories();
    }

    @PutMapping(value = "/{idCategory}")
    public Object updateCategory(@PathVariable(value = "idCategory") String idCategory, @RequestBody Category category) {

        log.info("Updating Category with id '{}'...", idCategory);
        Object resp = categoryService.updateCategory(idCategory, category);
        if(resp.getClass().getSimpleName().equals("String")) {
            log.error(resp);
            return resp;
        }

        Category categoryDB = (Category) resp;
        log.info("Category with id: '{}' UPDATED successfully", idCategory);
        return categoryDB;
    }

    @DeleteMapping(value = "/{idCategory}")
    public String deleteCategory(@PathVariable(value = "idCategory") String idCategory) {

        log.info("Deleting Category with id '{}'...", idCategory);
        Object resp = categoryService.deleteCategory(idCategory);
        if(resp.getClass().getSimpleName().equals("String")) {
            log.error(resp);
            return resp.toString();
        }

        log.info("Category with id: '{}' DELETED successfully", idCategory);
        return resp.toString();
    }
}