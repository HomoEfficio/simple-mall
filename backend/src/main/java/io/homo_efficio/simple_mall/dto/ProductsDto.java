package io.homo_efficio.simple_mall.dto;

import io.homo_efficio.simple_mall.domain.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author homo.efficio@gmail.com
 *         created on 2017-05-17
 */
@Getter
@Setter
public class ProductsDto {

    private String totalCount;

    private List<Product> product;
}
