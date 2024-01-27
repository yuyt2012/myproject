package study.myproject.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;
import study.myproject.domain.member.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {

    Optional<Member> findByLoginId(String loginId);
}
