package study.myproject.domain.member;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import study.myproject.dto.memberdto.MemberRegisterDTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    @Column(name = "login_id")
    private String loginId;
    private String password;
    private String role;

    @Embedded
    private PrivacyInfo privacyInfo;

    public Member(String loginId, String password, String role, PrivacyInfo privacyInfo) {
        this.loginId = loginId;
        this.password = password;
        this.role = role;
        this.privacyInfo = privacyInfo;
    }
}
