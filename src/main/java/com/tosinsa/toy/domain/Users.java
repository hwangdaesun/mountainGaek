package com.tosinsa.toy.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Entity(name = "USERS")
@RequiredArgsConstructor
@ToString
public class Users {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String usersId;

    @Column
    private String usersPassWord;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private LocalDateTime createdAt; // jpa 도메인 객체에는 일반적으로 포함됨.

    @Column
    private LocalDateTime updatedAt; // jpa 도메인 객체에는 일반적으로 포함됨. -> 이력 관리



    @Builder
    public Users(String name, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    public Users(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void updateEmail(String email){
        this.email = email;
    }
}
