package io.homo_efficio.simple_mall.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author homo.efficio@gmail.com
 *         created on 2017-05-17
 */
@Entity
@Getter
@Setter
@Table(name = "MEMBER")
public class Member extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @NotNull
    @Column(unique = true)
    private String uid;

//    @OneToOne
//    @JoinColumn(name = "basket_id")
//    private Basket basket;
//
//    @OneToMany(mappedBy = "orderer")
//    private List<Order> orders;
}
