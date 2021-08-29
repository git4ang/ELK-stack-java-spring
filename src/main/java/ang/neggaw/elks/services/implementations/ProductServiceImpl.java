package ang.neggaw.elks.services.implementations;

import ang.neggaw.elks.entities.Category;
import ang.neggaw.elks.entities.Product;
import ang.neggaw.elks.repositories.CategoryElasticRepository;
import ang.neggaw.elks.repositories.ProductElasticRepository;
import ang.neggaw.elks.services.ProductService;
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
public class ProductServiceImpl implements ProductService {

    private final ProductElasticRepository productElasticRepository;
    private final CategoryElasticRepository categoryElasticRepository;

    @Override
    public Object createProduct(Product p) {
        Category category = categoryElasticRepository.findByIdCategory(p.getCategory().getIdCategory());
        if(category == null) return String.format("Unable to create Product. Category with id: '%s' Not Found", p.getCategory().getIdCategory());
        p.setCategory(category);
        return productElasticRepository.save(p);
    }

    @Override
    public Product getProduct(String idProduct) {
        return productElasticRepository.findByIdProduct(idProduct);
    }

    @Override
    public Collection<Product> allProducts() {
        Collection<Product> products = new ArrayList<>();
        productElasticRepository.findAll().iterator().forEachRemaining(products::add);
        return products;
    }

    @Override
    public Object updateProduct(String idProduct, Product p) {

        Product productDB = productElasticRepository.findByIdProduct(idProduct);
        if(productDB == null) return String.format("Unable to update. Product with id: '%s' Not Found", idProduct);
        p.setCategory(productDB.getCategory());
        p.setIdProduct(productDB.getIdProduct());
        return productElasticRepository.save(p);
    }

    @Override
    public Object deleteProduct(String idProduct) {
        Product productDB = productElasticRepository.findByIdProduct(idProduct);
        if(productDB == null) return String.format("Unable to delete. Product with id: '%s' Not Found", idProduct);
        productElasticRepository.delete(productDB);
        if(!productElasticRepository.existsById(idProduct))
            return String.format("Unable to delete Product with '%s'.", idProduct);
        return String.format("Product with id: '%s' DELETE successfully", idProduct);
    }
}