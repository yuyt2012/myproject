package study.myproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.myproject.config.jwt.JwtTokenProvider;
import study.myproject.domain.member.Member;
import study.myproject.domain.member.PrivacyInfo;
import study.myproject.dto.memberdto.LoginDTO;
import study.myproject.dto.memberdto.MemberDTO;
import study.myproject.dto.memberdto.MemberRegisterDTO;
import study.myproject.dto.security.JwtToken;
import study.myproject.exception.DuplicationException;
import study.myproject.exception.NotExistMemberException;
import study.myproject.repository.jwt.RefreshTokenRepository;
import study.myproject.repository.member.MemberRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    //회원가입 하는 서비스 로직
    public Member save(MemberRegisterDTO memberRegisterDTO) {
        if (!checkLoginId(memberRegisterDTO.getLoginId())) {
            Member member = Member.builder()
                    .loginId(memberRegisterDTO.getLoginId())
                    .password(bCryptPasswordEncoder.encode(memberRegisterDTO.getPassword()))
                    .authority(memberRegisterDTO.getAuthority())
                    .privacyInfo(PrivacyInfo.builder()
                            .username(memberRegisterDTO.getUsername())
                            .age(memberRegisterDTO.getAge())
                            .city(memberRegisterDTO.getCity())
                            .street(memberRegisterDTO.getStreet())
                            .zipcode(memberRegisterDTO.getZipcode())
                            .build())
                    .build();
            return memberRepository.save(member);
        } else {
            throw new DuplicationException(memberRegisterDTO.getLoginId());
        }
    }

    @Transactional
    public JwtToken login(LoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginDTO.getLoginId(), loginDTO.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        return jwtTokenProvider.generateToken(authentication);
    }
    //회원찾기
    @Transactional(readOnly = true)
    public MemberDTO findByLoginId(String loginId) {
        Member member = memberRepository.findByLoginId(loginId)
                .orElseThrow(NotExistMemberException::new);
        return MemberDTO.builder()
                .loginId(member.getLoginId())
                .username(member.getPrivacyInfo().getUsername())
                .age(member.getPrivacyInfo().getAge())
                .build();
    }

    @Transactional(readOnly = true)
    public Page<MemberDTO> findAll(Pageable pageable) {
        Page<Member> findMembers = memberRepository.findAll(pageable);
        if (findMembers == null) {
            throw new NotExistMemberException();
        }
        Page<MemberDTO> members = findMembers.map(
                m -> MemberDTO.builder()
                        .loginId(m.getLoginId())
                        .username(m.getPrivacyInfo().getUsername())
                        .age(m.getPrivacyInfo().getAge())
                        .build());
        return members;
    }

    //아이디 중복 검사
    private Boolean checkLoginId(String loginId) {
        return memberRepository.existsMemberByLoginId(loginId);
    }
}
