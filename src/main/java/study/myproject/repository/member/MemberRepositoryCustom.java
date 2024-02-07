package study.myproject.repository.member;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import study.myproject.domain.member.Member;

public interface MemberRepositoryCustom {

    Page<Member> findAll(Pageable pageable);

}
