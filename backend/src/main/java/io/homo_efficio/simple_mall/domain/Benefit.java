package io.homo_efficio.simple_mall.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author homo.efficio@gmail.com
 *         created on 2017-05-17
 */
@Embeddable
@Getter
@Setter
public class Benefit implements Serializable {

    private String discount;

    private String mileage;

    private String point;

    private String inFree;
}
