package ang.neggaw.elks.repositories;

import ang.neggaw.elks.entities.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * author by: ANG
 * since: 29/08/2021 11:53
 */

@RepositoryRestResource
public interface ProductElasticRepository extends ElasticsearchRepository<Product, String> {
    Product findByIdProduct(String idProduct);
}
