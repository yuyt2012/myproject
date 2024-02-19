package study.myproject.domain.member;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.myproject.dto.memberdto.MemberRegisterDTO;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class PrivacyInfo {

    private String username;
    private int age;
    private String city;
    private String street;
    private String zipcode;
}
