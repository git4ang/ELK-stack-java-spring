package ang.neggaw.elks.services;

import ang.neggaw.elks.entities.Product;

import java.util.Collection;

/**
 * author by: ANG
 * since: 29/08/2021 12:13
 */

public interface ProductService {
    Object createProduct(Product p);
    Product getProduct(String idProduct);
    Collection<Product> allProducts();
    Object updateProduct(String idProduct, Product p);
    Object deleteProduct(String idProduct);
}
