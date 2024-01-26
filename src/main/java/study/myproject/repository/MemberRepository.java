package study.myproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.myproject.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
