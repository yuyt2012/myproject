package study.myproject.domain.member;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@ToString(of = {"loginId"})
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    private String loginId;
    private String password;
    private String role;

    @Embedded
    private PrivacyInfo privacyInfo;

    public Member(String loginId, String password, String role) {
        this.loginId = loginId;
        this.password = password;
        this.role = role;
    }

    public Member(String loginId, String password, String role, PrivacyInfo privacyInfo) {
        this.loginId = loginId;
        this.password = password;
        this.role = role;
        this.privacyInfo = privacyInfo;
    }
}
