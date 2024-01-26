package study.myproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.myproject.domain.Member;
import study.myproject.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member save(Member member) {
        return memberRepository.save(member);
    }
}
