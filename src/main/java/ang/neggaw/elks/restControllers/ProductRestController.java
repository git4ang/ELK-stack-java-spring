package ang.neggaw.elks.restControllers;

import ang.neggaw.elks.entities.Product;
import ang.neggaw.elks.services.ProductService;
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
@RequestMapping(value = "/api/products")
@RestController
public class ProductRestController {

    private final ProductService productService;

    @PostMapping
    public Object createProduct(@RequestBody Product product) {

        log.info("Creating Product with name: '{}'...", product.getName());
        Object resp = productService.createProduct(product);
        if(resp.getClass().getSimpleName().equals("String")) {
            log.error(resp);
            return resp;
        }

        Product productDB = (Product) resp;
        log.info("Product with id: '{}' CREATED successfully", productDB.getIdProduct());
        return productDB;
    }

    @GetMapping(value = "/{idProduct}")
    public Product getProduct(@PathVariable(value = "idProduct") String idProduct) {

        log.info("Fetching Product with id: '{}'...", idProduct);

        Product productDB = productService.getProduct(idProduct);
        if(productDB == null) {
            log.error("Unable to find Product with id: '{}'", idProduct);
            return null;
        }

        return productDB;
    }

    @GetMapping
    public Collection<Product> allProducts() {

        log.info("Fetching all products...");
        return productService.allProducts();

    }

    @PutMapping(value = "/{idProduct}")
    public Object updateProduct(@PathVariable(value = "idProduct") String idProduct, @RequestBody Product product) {

        log.info("Updating Product with id '{}'...", idProduct);
        Object resp = productService.updateProduct(idProduct, product);
        if(resp.getClass().getSimpleName().equals("String")) {
            log.error(resp);
            return resp;
        }

        Product productDB = (Product) resp;
        log.info("Product with id: '{}' UPDATED successfully", idProduct);
        return productDB;
    }

    @DeleteMapping(value = "/{idProduct}")
    public String deleteProduct(@PathVariable(value = "idProduct") String idProduct) {

        log.info("Deleting Product with id '{}'...", idProduct);
        Object resp = productService.deleteProduct(idProduct);
        if(resp.getClass().getSimpleName().equals("String")) {
            log.error(resp);
            return resp.toString();
        }

        log.info("Product with id: '{}' DELETED successfully", idProduct);
        return resp.toString();
    }
}