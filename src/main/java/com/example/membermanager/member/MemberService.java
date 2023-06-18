package com.example.membermanager.member;

import com.example.membermanager.common.exception.CustomException;
import com.example.membermanager.common.exception.ErrorCode;
import com.example.membermanager.member.dto.MemberRequestDto;
import com.example.membermanager.member.dto.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void createMember(MemberRequestDto memberRequestDto){

        Optional<Member> checkMemberId = memberRepository.findByMemberId(memberRequestDto.getMemberId());
        if(checkMemberId.isPresent()) throw new CustomException(ErrorCode.DUPLICATED_MEMBERID);

        Optional<Member> checkNickname = memberRepository.findByNickname(memberRequestDto.getNickname());
        if(checkNickname.isPresent()) throw new CustomException(ErrorCode.DUPLICATED_NICKNAME);

        memberRequestDto.encodePassword(
                passwordEncoder.encode(memberRequestDto.getPassword()));

        Member member = MemberMapper.INSTANCE.toMember(memberRequestDto);
        memberRepository.save(member);
    }
}
