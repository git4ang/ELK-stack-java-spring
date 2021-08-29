package ang.neggaw.elks.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * author by: ANG
 * since: 29/08/2021 11:50
 */

@Document(indexName = "products_elk")
@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Product implements Serializable {

    @Id
    @Field()
    private String idProduct;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Text)
    private String description;

    @Field(type = FieldType.Double)
    private double price;

    @Field(type = FieldType.Keyword)
    private Category category;
}