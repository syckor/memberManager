package com.example.membermanager.member;

import com.example.membermanager.member.dto.MemberRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemberMapper {

    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    //MemberRequestDto -> Member
    MemberRequestDto toMemberRequestDto(Member member);

    //Member -> MemberRequestDto
    Member toMember(MemberRequestDto memberRequestDto);
}
