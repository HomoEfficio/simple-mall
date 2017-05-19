package io.homo_efficio.simple_mall.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @author homo.efficio@gmail.com
 *         created on 2017-05-18
 */
@Entity
@Getter
@Setter
@Table(name = "BASKET")
public class Basket {

    @Id
    @GeneratedValue
    @Column(name = "basket_id")
    private Long id;

    @Embedded
    @AttributeOverrides(
            @AttributeOverride(name = "id", column = @Column(name = "member_id"))
    )
    private MemberId memberId;

    @Embedded
    private List<BasketItem> basketItems;
}
