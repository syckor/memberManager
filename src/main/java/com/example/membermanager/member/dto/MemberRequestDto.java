package com.example.membermanager.member.dto;

import lombok.Getter;


@Getter
public class MemberRequestDto {

    private String memberId;
    private String password;
    private String nickname;

    public void encodePassword(String password){
        this.password = password;
    }
}
