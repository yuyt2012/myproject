package study.myproject.service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import study.myproject.domain.member.Member;
import study.myproject.dto.security.CustomUserDetails;
import study.myproject.repository.member.MemberRepository;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;


    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        Member member = memberRepository.findByLoginId(loginId);
        if (member != null) {
            return new CustomUserDetails(member);
        } else {
            throw new UsernameNotFoundException("User not found with loginId: " + loginId);
        }
    }
}
