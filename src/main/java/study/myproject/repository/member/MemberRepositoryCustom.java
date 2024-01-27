package study.myproject.repository.member;

import study.myproject.dto.memberdto.LoginDTO;
import study.myproject.dto.memberdto.MemberDTO;
import study.myproject.dto.memberdto.MemberRegisterDTO;

import java.util.Optional;

public interface MemberRepositoryCustom {

    Boolean existByLoginId(String loginId);
}
