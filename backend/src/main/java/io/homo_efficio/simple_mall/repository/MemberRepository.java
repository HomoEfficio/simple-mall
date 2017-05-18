package io.homo_efficio.simple_mall.repository;

import io.homo_efficio.simple_mall.domain.Member;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author homo.efficio@gmail.com
 *         created on 2017-05-18
 */
@RepositoryRestResource
public interface MemberRepository extends PagingAndSortingRepository<Member, Long> {
}
