package study.myproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.myproject.domain.member.Member;
import study.myproject.dto.memberdto.MemberDTO;
import study.myproject.exception.DuplicationException;
import study.myproject.repository.member.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    //회원가입 하는 서비스 로직
    public Member save(Member member) {
        if (duplicationLoginId(member.getLoginId())) {
            return memberRepository.save(member);
        } else {
            throw new DuplicationException(member.getLoginId());
        }
    }

    public MemberDTO findByLoginId(String loginId) {
        return memberRepository.findByLoginId(loginId);
    }

    //아이디 중복 검사
    private Boolean duplicationLoginId(String loginId) {
        return memberRepository.findByLoginId(loginId) == null;
    }
}
