package study.myproject.dto.memberdto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberRegisterDTO {

    @NotEmpty(message = "아이디를 입력해야 합니다.")
    private String username;
    @NotEmpty(message = "비밀번호를 입력해야 합니다.")
    private String password;
    @NotEmpty(message = "이름을 입력해야 합니다.")
    private String name;
    private String role;
    private int age;
    private String city;
    private String street;
    private String zipcode;
}
