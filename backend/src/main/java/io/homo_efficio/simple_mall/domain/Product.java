package io.homo_efficio.simple_mall.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author homo.efficio@gmail.com
 *         created on 2017-05-17
 */
@Entity
@Getter
@Setter
public class Product extends BaseEntity implements Serializable {

    @Id
    @NotNull
    @Column(name = "product_code")
    private String productCode;

    @NotNull
    private String productName;

    @NotNull
    private Integer productPrice;

    @NotNull
    private String productImage;

    private String productImage100;

    private String productImage110;

    private String productImage120;

    private String productImage130;

    private String productImage140;

    private String productImage150;

    private String productImage170;

    private String productImage200;

    private String productImage250;

    private String productImage270;

    private String productImage300;

    private String text1;

    private String text2;

    private String sellerNick;

    private String seller;

    private String sellerGrd;

    private String rating;

    @NotNull
    private String detailPageUrl;

    private String salePrice;

    private String delivery;

    private String reviewCount;

    private String buySatisfy;

    private String minorYn;

    @Embedded
    private Benefit benefit;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
