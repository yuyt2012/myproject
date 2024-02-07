package study.myproject.repository.member;

import static study.myproject.domain.member.QMember.*;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import study.myproject.domain.member.Member;

public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public MemberRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }
    @Override
    public Page<Member> findAll(Pageable pageable) {
        List<Member> members = queryFactory
                .selectFrom(member)
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(member.count())
                .from(member);
        return PageableExecutionUtils.getPage(members, pageable, countQuery::fetchOne);
    }
}
