package study.myproject.repository.member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.myproject.domain.member.Member;
import study.myproject.domain.member.PrivacyInfo;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void test1() {
        Member member = new Member("member1", "123", "asd", new PrivacyInfo("asd", 123, "asd", "asd", "asd"));
        Member save = memberRepository.save(member);

        Member member1 = memberRepository.findByLoginId("member1");

        System.out.println("member1 = " + member1);

    }

}