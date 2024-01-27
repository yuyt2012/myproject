package study.myproject.repository.member;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import study.myproject.domain.member.Member;
import study.myproject.domain.member.QMember;
import study.myproject.dto.memberdto.LoginDTO;
import study.myproject.dto.memberdto.MemberDTO;
import study.myproject.dto.memberdto.MemberRegisterDTO;

import java.util.Optional;

import static study.myproject.domain.member.QMember.*;

public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public MemberRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Boolean existByLoginId(String loginId) {
        Member findMember = queryFactory
                .select(member)
                .where(member.loginId.eq(loginId))
                .from(member)
                .fetchOne();
        return findMember != null;
    }
}
