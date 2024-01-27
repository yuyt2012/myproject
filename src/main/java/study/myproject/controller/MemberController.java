package study.myproject.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import study.myproject.domain.member.Member;
import study.myproject.dto.memberdto.LoginDTO;
import study.myproject.dto.memberdto.LoginSuccessDTO;
import study.myproject.dto.memberdto.MemberDTO;
import study.myproject.dto.memberdto.MemberRegisterDTO;
import study.myproject.exception.DuplicationException;
import study.myproject.exception.WrongIdException;
import study.myproject.exception.WrongPasswordException;
import study.myproject.service.MemberService;

import static study.myproject.domain.member.Member.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    //회원 가입
    @PostMapping("/members/new")
    public ResponseEntity<String> joinMember(@RequestBody @Valid MemberRegisterDTO memberRegisterDTO, BindingResult result) {
        try {
            if (result.hasErrors()) {
                StringBuilder errorMessage = new StringBuilder("회원 가입 실패 ->\n");
                for (FieldError error : result.getFieldErrors()) {
                    errorMessage.append(error.getDefaultMessage()).append("\n");
                }
                errorMessage.delete(errorMessage.length() - 2, errorMessage.length());
                return ResponseEntity.badRequest().body(errorMessage.toString());
            }
            Member member = convertToMemberEntity(memberRegisterDTO);
            Member savedMember = memberService.save(member);
            return ResponseEntity.status(HttpStatus.CREATED).body("회원 가입 성공 -> " + savedMember.getId() + "번째 회원 입니다.");

        } catch (DuplicationException e) {
            return ResponseEntity.badRequest().body("회원 가입 실패 -> " + e.getMessage());
        }
    }

    @PostMapping("/members/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        try {
            LoginSuccessDTO login = memberService.login(loginDTO.getLoginId(), loginDTO.getPassword());
            return ResponseEntity.status(HttpStatus.OK).body(login.toString());
        } catch (WrongIdException | WrongPasswordException e) {
            return ResponseEntity.badRequest().body("로그인 실패 -> " + e.getMessage());
        }
    }

    @GetMapping("/members/find/{loginId}")
    public ResponseEntity<MemberDTO> findMemberByLoginId(@PathVariable String loginId) {
        MemberDTO findMember = memberService.findByLoginId(loginId);
        if (findMember != null) {
            return ResponseEntity.status(HttpStatus.OK).body(findMember);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
