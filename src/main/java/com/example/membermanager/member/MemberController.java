package com.example.membermanager.member;

import com.example.membermanager.member.dto.MemberRequestDto;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("")
    public ResponseEntity<String> createMember(@RequestBody MemberRequestDto memberRequestDto){
        memberService.createMember(memberRequestDto);
        return new ResponseEntity<>("회원가입 성공", HttpStatus.OK);
    }

    @PostMapping("signIn")
    public ResponseEntity<String> signIn(@RequestBody MemberRequestDto memberRequestDto, HttpServletResponse response){
        memberService.signIn(memberRequestDto, response);
        return new ResponseEntity<>("로그인 성공, 헤더 확인", HttpStatus.OK);
    }

}
