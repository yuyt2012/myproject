package study.myproject.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;
import study.myproject.domain.member.Member;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {
}
