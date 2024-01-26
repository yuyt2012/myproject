package study.myproject.dto.memberdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.myproject.domain.member.Member;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

    private String loginId;
    private String username;
    private int age;

    public static MemberDTO convertToMemberDTO(Member member) {
        if (member != null) {
            return new MemberDTO(
                    member.getLoginId(),
                    member.getPrivacyInfo().getUsername(),
                    member.getPrivacyInfo().getAge());
        }
        return null;
    }
}
