package study.myproject.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;
import study.myproject.domain.member.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByLoginId(String loginId);

    boolean existsMemberByLoginId(String loginId);
}
