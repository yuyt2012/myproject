package study.myproject.repository.member;

import study.myproject.dto.memberdto.MemberDTO;

public interface MemberRepositoryCustom {

    MemberDTO findByLoginId(String loginId);
}
