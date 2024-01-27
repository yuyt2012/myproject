package study.myproject.dto.memberdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.myproject.domain.member.Member;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

    private String loginId;
    private String password;

    public static LoginDTO convertToLoginDTO(Member member) {
        if (member != null) {
            return new LoginDTO(
                    member.getLoginId(),
                    member.getPassword());
        }
        return null;
    }
}
