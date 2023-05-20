package com.tosinsa.toy.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Getter
@Entity(name = "USERS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Users {

    @Id @GeneratedValue
    @Column(name = "users_id")
    private Long id;

    @Column
    private String loginId;

    @Column
    private String loginPassWord;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private Instant createdAt; // jpa 도메인 객체에는 일반적으로 포함됨.

    @Column
    private Instant updatedAt; // jpa 도메인 객체에는 일반적으로 포함됨. -> 이력 관리

    @Embedded
    private Address address;

    @Builder
    public Users(String name, String email, Address address) {
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public static Users createUsers(String name, String email, Address address){
        Users users = new Users(name, email, address);
        return users;
    }

    @PrePersist
    public void prePersist(){
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    @PreUpdate
    public void preUpdate(){
        this.updatedAt = Instant.now();
    }

    public void updateEmail(String email){
        this.email = email;
    }
}
