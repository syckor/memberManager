package com.example.membermanager.member;

import com.example.membermanager.common.config.JwtUtil;
import com.example.membermanager.common.exception.CustomException;
import com.example.membermanager.common.exception.ErrorCode;
import com.example.membermanager.member.dto.MemberRequestDto;
import com.example.membermanager.member.dto.MemberResponseDto;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final ConcurrentHashMap<String, String> loginStatus;

    @Transactional
    public void createMember(MemberRequestDto memberRequestDto){

        Optional<Member> checkMemberId = memberRepository.findByMemberId(memberRequestDto.getMemberId());
        if(checkMemberId.isPresent()) throw new CustomException(ErrorCode.DUPLICATED_MEMBERID);

        Optional<Member> checkNickname = memberRepository.findByNickname(memberRequestDto.getNickname());
        if(checkNickname.isPresent()) throw new CustomException(ErrorCode.DUPLICATED_NICKNAME);

        passwordEncoder(memberRequestDto);

        Member member = MemberMapper.INSTANCE.toMember(memberRequestDto);
        memberRepository.save(member);
    }

    @Transactional
    public void signIn(MemberRequestDto memberRequestDto, HttpServletResponse response){

        String memberId = memberRequestDto.getMemberId();
        String password = memberRequestDto.getPassword();

        Member member = memberRepository.findByMemberId(memberId).orElseThrow(
                ()->new CustomException(ErrorCode.NOT_FOUND_MEMBER)
        );

        if(!passwordEncoder.matches(password, member.getPassword())){
            throw new CustomException(ErrorCode.NOT_FOUND_MEMBER);
        }

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(member.getMemberId()));

    }

    private void passwordEncoder(MemberRequestDto memberRequestDto){
        memberRequestDto.encodePassword(passwordEncoder.encode(memberRequestDto.getPassword()));
    }


}
