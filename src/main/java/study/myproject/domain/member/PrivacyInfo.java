package study.myproject.domain.member;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.myproject.dto.memberdto.MemberRegisterDTO;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PrivacyInfo {

    private String name;
    private int age;
    private String city;
    private String street;
    private String zipcode;

    public static PrivacyInfo createPrivacyInfoFromDTO(MemberRegisterDTO memberRegisterDTO) {
        return new PrivacyInfo(
                memberRegisterDTO.getName(),
                memberRegisterDTO.getAge(),
                memberRegisterDTO.getCity(),
                memberRegisterDTO.getStreet(),
                memberRegisterDTO.getZipcode()
        );
    }
}
