package study.myproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.myproject.domain.member.Member;
import study.myproject.dto.memberdto.LoginDTO;
import study.myproject.dto.memberdto.LoginSuccessDTO;
import study.myproject.dto.memberdto.MemberDTO;
import study.myproject.exception.DuplicationException;
import study.myproject.exception.WrongIdException;
import study.myproject.exception.WrongPasswordException;
import study.myproject.repository.member.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    //회원가입 하는 서비스 로직
    public Member save(Member member) {
        if (checkLoginId(member.getLoginId())) {
            return memberRepository.save(member);
        } else {
            throw new DuplicationException(member.getLoginId());
        }
    }

    //회원찾기
    public MemberDTO findByLoginId(String loginId) {
        return memberRepository.findByLoginId(loginId);
    }

    //로그인
    public LoginSuccessDTO login(String loginId, String password) {
        LoginDTO byLoginID = memberRepository.findByLoginID(loginId);
        if (byLoginID != null) {
            if (byLoginID.getPassword().equals(password)) {
                return new LoginSuccessDTO(byLoginID.getLoginId());
            } else {
                throw new WrongPasswordException();
            }
        } else {
            throw new WrongIdException();
        }
    }

    //아이디 중복 검사
    private Boolean checkLoginId(String loginId) {
        return memberRepository.findByLoginId(loginId) == null;
    }
}
