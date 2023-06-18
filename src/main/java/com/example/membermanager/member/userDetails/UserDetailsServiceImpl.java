package com.example.membermanager.member.userDetails;

import com.example.membermanager.member.Member;
import com.example.membermanager.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final MemberRepository memberRepository;


    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        Member member = memberRepository.findByMemberId(memberId).orElseThrow(
                ()-> new UsernameNotFoundException("Not found user")
        );
        return new UserDetailsImpl(member.getMemberId(), member);
    }
}
