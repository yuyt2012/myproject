package study.myproject.dto.memberdto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.myproject.domain.member.Authority;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberRegisterDTO {

    @NotEmpty(message = "아이디를 입력해야 합니다.")
    private String loginId;
    @NotEmpty(message = "비밀번호를 입력해야 합니다.")
    private String password;
    @NotEmpty(message = "이름을 입력해야 합니다.")
    private String username;
    private Authority authority;
    private int age;
    private String city;
    private String street;
    private String zipcode;
}
