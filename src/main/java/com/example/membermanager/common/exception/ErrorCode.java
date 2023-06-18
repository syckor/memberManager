package com.example.membermanager.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    DUPLICATED_MEMBERID("중복된 아이디"),
    DUPLICATED_NICKNAME("중복된 닉네임");

    private final String msg;
}
