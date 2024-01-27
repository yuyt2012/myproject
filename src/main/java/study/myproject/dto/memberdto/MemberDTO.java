package study.myproject.dto.memberdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.myproject.domain.member.Member;

import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

    private String loginId;
    private String username;
    private int age;
}
