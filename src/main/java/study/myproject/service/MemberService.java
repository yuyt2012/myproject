package study.myproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.myproject.domain.member.Member;
import study.myproject.domain.member.PrivacyInfo;
import study.myproject.dto.memberdto.LoginDTO;
import study.myproject.dto.memberdto.MemberDTO;
import study.myproject.dto.memberdto.MemberRegisterDTO;
import study.myproject.exception.DuplicationException;
import study.myproject.exception.NotExistMemberException;
import study.myproject.exception.WrongIdException;
import study.myproject.exception.WrongPasswordException;
import study.myproject.repository.member.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    //회원가입 하는 서비스 로직
    @Transactional
    public Member save(MemberRegisterDTO memberRegisterDTO) {
        if (!checkLoginId(memberRegisterDTO.getLoginId())) {
            Member member = new Member(memberRegisterDTO.getLoginId(), bCryptPasswordEncoder.encode(memberRegisterDTO.getPassword()),
                    memberRegisterDTO.getRole(), new PrivacyInfo(memberRegisterDTO.getUsername(),
                    memberRegisterDTO.getAge(), memberRegisterDTO.getCity(), memberRegisterDTO.getStreet(), memberRegisterDTO.getZipcode()));
            return memberRepository.save(member);
        } else {
            throw new DuplicationException(memberRegisterDTO.getLoginId());
        }
    }

    //회원찾기
    @Transactional(readOnly = true)
    public MemberDTO findByLoginId(String loginId) {
        Member member = memberRepository.findByLoginId(loginId)
                .orElseThrow(NotExistMemberException::new);
        return new MemberDTO(member.getLoginId(), member.getPrivacyInfo().getUsername(), member.getPrivacyInfo().getAge());
    }

    public boolean login(LoginDTO loginDTO) {
        Member member = memberRepository.findByLoginId(loginDTO.getLoginId())
                .orElseThrow(WrongIdException::new);

        if (bCryptPasswordEncoder.matches(loginDTO.getPassword(), member.getPassword())) {
            // 비밀번호가 일치하면 로그인 성공
            return true;
        } else {
            // 비밀번호가 일치하지 않으면 로그인 실패
            throw new WrongPasswordException();
        }
    }

    //아이디 중복 검사
    private Boolean checkLoginId(String loginId) {
        return memberRepository.existByLoginId(loginId);
    }
}
