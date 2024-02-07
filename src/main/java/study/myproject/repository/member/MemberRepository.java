package study.myproject.repository.member;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import study.myproject.domain.member.Member;

import java.util.Optional;
import study.myproject.dto.memberdto.MemberDTO;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {

    Optional<Member> findByLoginId(String loginId);

    boolean existsMemberByLoginId(String loginId);
}
