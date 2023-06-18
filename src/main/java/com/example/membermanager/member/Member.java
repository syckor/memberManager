package com.example.membermanager.member;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String memberId;
    @Column
    private String password;
    @Column
    private String nickname;

    @Column
    private Boolean isLock;

    @Builder
    public Member(String memberId, String password, String nickname){
        this.memberId = memberId;
        this.password = password;
        this.nickname = nickname;
    }

    public void update(String password, String nickname){
        this.password = password;
        this.nickname = nickname;
    }


}
