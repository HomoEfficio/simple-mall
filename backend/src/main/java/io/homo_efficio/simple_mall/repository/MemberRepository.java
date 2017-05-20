package io.homo_efficio.simple_mall.repository;

import io.homo_efficio.simple_mall.domain.Member;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author homo.efficio@gmail.com
 *         created on 2017-05-18
 */
@RepositoryRestResource(path = "members")
//@Repository
public interface MemberRepository extends PagingAndSortingRepository<Member, Long> {

    Optional<Member> findByUserName(String userName);
}
