package io.homo_efficio.simple_mall.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author homo.efficio@gmail.com
 *         created on 2017-05-18
 */
@Embeddable
@Getter
@Setter
public class MemberId implements Serializable{

    @Column(name = "member_id")
    private String id;
}
