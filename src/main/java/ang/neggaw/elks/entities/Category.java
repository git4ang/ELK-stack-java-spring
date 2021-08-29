package ang.neggaw.elks.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Collection;

/**
 * author by: ANG
 * since: 29/08/2021 11:50
 */

@Document(indexName = "categories_elk")
@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Category implements Serializable {

    @Id
    private String idCategory;

    @Field(type = FieldType.Keyword)
    private String name;

    @Field(type = FieldType.Auto)
    private Collection<Product> products;
}
