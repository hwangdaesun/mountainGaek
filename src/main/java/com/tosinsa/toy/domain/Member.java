package com.tosinsa.toy.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_email")
    private String email;

    @Column(name = "member_loginId")
    private String loginId;

    @Column(name = "member_password")
    private String password;

    @Column(name = "member_name")
    private String name;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @Builder
    public Member(String email, String loginId, String password, String name){
        this.email = email;
        this.loginId = loginId;
        this.password = password;
        this.name = name;
    }

    public static Member createMember(String email, String mId, String password, String name){
        Member member = Member.builder()
                .email(email)
                .loginId(mId)
                .password(password)
                .name(name)
                .build();
        return member;
    }

    public void mappingOrder(Order order){
        this.orders.add(order);
    }


}
