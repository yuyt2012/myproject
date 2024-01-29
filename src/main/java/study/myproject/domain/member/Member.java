package study.myproject.domain.member;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@ToString(of = {"username"})
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    private String username;
    private String password;
    private String role;

    @Embedded
    private PrivacyInfo privacyInfo;

    public Member(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Member(String username, String password, String role, PrivacyInfo privacyInfo) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.privacyInfo = privacyInfo;
    }
}
