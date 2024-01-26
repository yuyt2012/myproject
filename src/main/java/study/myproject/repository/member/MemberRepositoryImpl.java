package study.myproject.repository.member;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import study.myproject.domain.member.Member;
import study.myproject.domain.member.QMember;
import study.myproject.dto.memberdto.MemberDTO;

import static study.myproject.domain.member.QMember.*;

public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public MemberRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public MemberDTO findByLoginId(String loginId) {
        Member findMember = queryFactory
                .select(member)
                .where(member.loginId.eq(loginId))
                .from(member)
                .fetchOne();
        return MemberDTO.convertToMemberDTO(findMember);
    }
}
