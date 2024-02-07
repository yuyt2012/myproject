package study.myproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.myproject.domain.member.Member;
import study.myproject.domain.member.PrivacyInfo;
import study.myproject.dto.memberdto.MemberDTO;
import study.myproject.dto.memberdto.MemberRegisterDTO;
import study.myproject.exception.DuplicationException;
import study.myproject.exception.NotExistMemberException;
import study.myproject.repository.member.MemberRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    //회원가입 하는 서비스 로직
    public Member save(MemberRegisterDTO memberRegisterDTO) {
        if (!checkLoginId(memberRegisterDTO.getLoginId())) {
            Member member = new Member(memberRegisterDTO.getLoginId(),
                    bCryptPasswordEncoder.encode(memberRegisterDTO.getPassword()),
                    memberRegisterDTO.getRole(), new PrivacyInfo(memberRegisterDTO.getUsername(),
                    memberRegisterDTO.getAge(), memberRegisterDTO.getCity(), memberRegisterDTO.getStreet(),
                    memberRegisterDTO.getZipcode()));
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
        return new MemberDTO(member.getLoginId(), member.getPrivacyInfo().getUsername(),
                member.getPrivacyInfo().getAge());
    }

    @Transactional(readOnly = true)
    public Page<MemberDTO> findAll(Pageable pageable) {
        Page<Member> findMembers = memberRepository.findAll(pageable);
        if (findMembers == null) {
            throw new NotExistMemberException();
        }
        Page<MemberDTO> members = findMembers.map(
                m -> new MemberDTO(m.getLoginId(), m.getPrivacyInfo().getUsername(), m.getPrivacyInfo().getAge()));
        return members;
    }

    //아이디 중복 검사
    private Boolean checkLoginId(String loginId) {
        return memberRepository.existsMemberByLoginId(loginId);
    }
}
