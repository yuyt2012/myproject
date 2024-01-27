package study.myproject.repository.member;

import study.myproject.dto.memberdto.LoginDTO;
import study.myproject.dto.memberdto.MemberDTO;
import study.myproject.dto.memberdto.MemberRegisterDTO;

public interface MemberRepositoryCustom {

    MemberDTO findByLoginId(String loginId);

    LoginDTO findByLoginID(String loginId);
}
