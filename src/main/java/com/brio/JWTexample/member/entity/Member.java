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
    List<String> roles = new ArrayList<>();


}
