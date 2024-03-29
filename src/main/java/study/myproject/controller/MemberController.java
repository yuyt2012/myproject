package study.myproject.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import study.myproject.domain.member.Member;
import study.myproject.dto.memberdto.LoginDTO;
import study.myproject.dto.memberdto.MemberDTO;
import study.myproject.dto.memberdto.MemberRegisterDTO;
import study.myproject.dto.security.JwtToken;
import study.myproject.exception.*;
import study.myproject.service.member.MemberService;


@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    //회원 가입
    @PostMapping("/api/members/join")
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
            Member savedMember = memberService.save(memberRegisterDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("회원 가입 성공 -> " + savedMember.getId() + "번째 회원 입니다.");

        } catch (DuplicationException e) {
            return ResponseEntity.badRequest().body("회원 가입 실패 -> " + e.getMessage());
        }
    }

    @PostMapping("/api/members/login")
    public ResponseEntity<JwtToken> login(@RequestBody LoginDTO loginDTO) {
        JwtToken login = memberService.login(loginDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + login.getAccessToken());
        return new ResponseEntity<>(login, headers, HttpStatus.OK);
    }

    @GetMapping("/api/members/find/{loginId}")
    public ResponseEntity<Object> findMemberByLoginId(@PathVariable String loginId) {
        try {
            MemberDTO findMember = memberService.findByLoginId(loginId);
            return ResponseEntity.status(HttpStatus.OK).body(findMember);
        } catch (NotExistMemberException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/api/members/findall")
    public ResponseEntity<Object> findMemberAll(Pageable pageable) {
        try {
            Page<MemberDTO> findMembers = memberService.findAll(pageable);
            return ResponseEntity.status(HttpStatus.OK).body(findMembers);
        } catch (NotExistMemberException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
}
