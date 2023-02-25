package com.brio.JWTexample.member.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Member {
    Long memberId;

    String name;

    String email;

    String password;

    MemberStatus memberStatus = MemberStatus.MEMBER_NEW;

    // 멤버 권한
    private List<String> roles = new ArrayList<>();

    // 연관관계 매핑

    public enum MemberStatus{

        MEMBER_NEW("MEMBER_NEW"),
        MEMBER_ACTIVE("MEMBER_ACTIVE"),
        MEMBER_SLEEP("MEMBER_SLEEP"),
        MEMBER_QUIT("MEMBER_QUIT");

        @Getter
        String status;

        MemberStatus(String status) {
            this.status = status;
        }
    }

}
