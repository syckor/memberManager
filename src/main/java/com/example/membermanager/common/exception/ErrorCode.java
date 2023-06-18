package com.example.membermanager.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    DUPLICATED_MEMBERID("중복된 아이디"),
    DUPLICATED_NICKNAME("중복된 닉네임"),
    NOT_FOUND_MEMBER("일치하지 않는 회원");

    private final String msg;
}
