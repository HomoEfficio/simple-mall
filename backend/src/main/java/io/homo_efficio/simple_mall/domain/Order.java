package io.homo_efficio.simple_mall.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author homo.efficio@gmail.com
 *         created on 2017-05-17
 */
@Entity
@Getter
@Setter
@Table(name = "ORDERS")
public class Order extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member orderer;

    @OneToMany(mappedBy = "order")
    private List<Product> products;
}
