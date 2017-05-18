package io.homo_efficio.simple_mall.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

/**
 * @author homo.efficio@gmail.com
 *         created on 2017-05-18
 */
@Embeddable
@Getter
@Setter
public class BasketItem {

    private Product product;

    private int count;
}
