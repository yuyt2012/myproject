package study.myproject.domain.member;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.myproject.dto.memberdto.MemberRegisterDTO;

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

    @Embedded
    private PrivacyInfo privacyInfo;

    protected Member(String loginId, String password, PrivacyInfo privacyInfo) {
        this.loginId = loginId;
        this.password = password;
        this.privacyInfo = privacyInfo;
    }

    public static Member convertToMemberEntity(MemberRegisterDTO memberRegisterDTO) {
        PrivacyInfo privacyInfo = PrivacyInfo.createPrivacyInfoFromDTO(memberRegisterDTO);
        return new Member(memberRegisterDTO.getLoginId(), memberRegisterDTO.getPassword(),privacyInfo);
    }
}
